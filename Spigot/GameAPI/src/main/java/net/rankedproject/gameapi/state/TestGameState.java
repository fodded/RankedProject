package net.rankedproject.gameapi.state;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class TestGameState implements GameState {

    @NotNull
    @Override
    public GameStateBehavior behavior() {
        return GameStateBehavior.builder()
                .startAction(game -> {

                })
                .stopAction(game -> {

                })
                .duration(Duration.ofMinutes(30))
                .build();
    }
}
