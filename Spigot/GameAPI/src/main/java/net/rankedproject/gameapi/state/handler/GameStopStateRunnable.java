package net.rankedproject.gameapi.state.handler;

import lombok.RequiredArgsConstructor;
import net.rankedproject.gameapi.Game;

@RequiredArgsConstructor
public class GameStopStateRunnable implements Runnable {

    private final Game game;

    @Override
    public void run() {
        var stateContext = game.getStateContext();
        var currentState = stateContext.getCurrentState();

        var behavior = currentState.behavior();
        var stopAction = behavior.getStopAction();

        if (stopAction != null) {
            stopAction.accept(game);
        }

        stateContext.switchNextState();
    }
}
