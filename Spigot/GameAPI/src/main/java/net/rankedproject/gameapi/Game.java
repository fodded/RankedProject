package net.rankedproject.gameapi;

import lombok.Getter;
import net.rankedproject.game.tracker.GameTracker;
import net.rankedproject.gameapi.event.GameEventContext;
import net.rankedproject.gameapi.event.type.GameStartEvent;
import net.rankedproject.gameapi.event.type.GameStopEvent;
import net.rankedproject.gameapi.mechanic.GameMechanicContext;
import net.rankedproject.gameapi.metadata.GameMetadata;
import net.rankedproject.gameapi.player.GamePlayerTracker;
import net.rankedproject.gameapi.scheduler.GameSchedulerContext;
import net.rankedproject.gameapi.state.GameState;
import net.rankedproject.gameapi.state.handler.GameStateContext;
import net.rankedproject.gameapi.world.GameWorldContext;
import net.rankedproject.spigot.CommonPlugin;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class Game {

    @Getter
    protected boolean started = false;

    protected final CommonPlugin plugin;
    protected final GameMetadata metadata;

    protected final GameEventContext eventContext;
    protected final GameStateContext stateContext;
    protected final GameSchedulerContext schedulerContext;
    protected final GameWorldContext worldContext;

    protected final GameMechanicContext mechanicContext = new GameMechanicContext();
    protected final GamePlayerTracker playerTracker = new GamePlayerTracker();

    public Game(@NotNull CommonPlugin plugin, @NotNull GameMetadata metadata) {
        this.plugin = plugin;
        this.metadata = metadata;

        this.eventContext = new GameEventContext(this, plugin);
        this.stateContext = new GameStateContext(this);
        this.schedulerContext = new GameSchedulerContext(this);
        this.worldContext = new GameWorldContext(this);
    }

    public void start() {
        stateContext.switchNextState();

        var pluginManager = Bukkit.getPluginManager();
        pluginManager.callEvent(new GameStartEvent(this));

        var gameTracker = plugin.getInjector().getInstance(GameTracker.class);
        gameTracker.track(this);

        started = true;
    }

    public void stop() {
        started = false;

        var pluginManager = Bukkit.getPluginManager();
        pluginManager.callEvent(new GameStopEvent(this));

        var gameTracker = plugin.getInjector().getInstance(GameTracker.class);
        gameTracker.untrack(this);

        mechanicContext.disableAll();
        eventContext.unregisterAll();
        worldContext.unload();
    }

    @NotNull
    public abstract GameState getInitState();
}