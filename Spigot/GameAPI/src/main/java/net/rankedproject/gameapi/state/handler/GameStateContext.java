package net.rankedproject.gameapi.state.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.state.GameState;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
public class GameStateContext {

    private GameState currentState;

    private final Game game;

    public void switchNextState() {
        cleanEventContext();
        if (currentState == null) {
            startInitState();
            return;
        }

        var currentBehavior = currentState.behavior();
        var nextState = currentBehavior.getNextState();

        if (nextState != null) {
            activateState(nextState);
        } else {
            game.stop();
        }
    }

    private void startInitState() {
        var initState = game.getInitState();
        activateState(initState);
    }

    private void cleanEventContext() {
        var eventContext = game.getEventContext();
        eventContext.unregisterAll();
    }

    private void activateState(@NotNull GameState state) {
        var behavior = state.behavior();
        var duration = behavior.getDuration();
        if (duration != null) {
            var plugin = game.getPlugin();
            var gameStopStateRunnable = new GameStopStateRunnable(game);

            var scheduler = Bukkit.getScheduler();
            scheduler.runTaskLater(plugin, gameStopStateRunnable, duration.toSeconds() * 20);
        }

        var startAction = behavior.getStartAction();
        startAction.accept(game);

        currentState = state;
    }
}