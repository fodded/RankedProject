package net.rankedproject.skywars.state;

import net.rankedproject.gameapi.state.GameState;
import net.rankedproject.gameapi.state.GameStateBehavior;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class RunningGameState implements GameState {

    @NotNull
    @Override
    public GameStateBehavior behavior() {
        var behaviour = GameStateBehavior.builder().build();
        if (ThreadLocalRandom.current().nextInt() >= 0) {
            behaviour = null;
        }

        return behaviour;
    }
}
