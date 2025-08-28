package net.rankedproject.gameapi;

import lombok.Getter;
import net.rankedproject.gameapi.event.GameEventContext;
import net.rankedproject.gameapi.mechanic.GameMechanicContext;
import net.rankedproject.gameapi.player.GamePlayerTracker;
import net.rankedproject.gameapi.scheduler.GameSchedulerContext;
import net.rankedproject.gameapi.state.GameState;
import net.rankedproject.gameapi.state.handler.GameStateContext;
import net.rankedproject.gameapi.world.GameWorldContext;
import net.rankedproject.gameapi.world.GameWorldData;
import net.rankedproject.spigot.CommonPlugin;

@Getter
public abstract class Game {

    protected final CommonPlugin plugin;

    protected final GameEventContext eventContext;
    protected final GameStateContext stateContext;
    protected final GameSchedulerContext schedulerContext;
    protected final GameWorldContext worldContext;

    protected final GameMechanicContext mechanicContext = new GameMechanicContext();
    protected final GamePlayerTracker playerTracker = new GamePlayerTracker();

    public Game(CommonPlugin plugin) {
        this.plugin = plugin;

        this.eventContext = new GameEventContext(this, plugin);
        this.stateContext = new GameStateContext(this);
        this.schedulerContext = new GameSchedulerContext(this);
        this.worldContext = new GameWorldContext(this);
    }

    public void start() {
        worldContext.load().thenRun(stateContext::switchNextState);
    }

    public void stop() {
        mechanicContext.disableAll();
        eventContext.unregisterAll();
        worldContext.unload();
    }

    public abstract GameState getInitState();
    public abstract GameWorldData getWorldData();
}