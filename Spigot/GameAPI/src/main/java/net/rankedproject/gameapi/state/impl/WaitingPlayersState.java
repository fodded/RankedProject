package net.rankedproject.gameapi.state.impl;

import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.mechanic.impl.NoBreakBlockMechanic;
import net.rankedproject.gameapi.mechanic.impl.NoPlayerDamageMechanic;
import net.rankedproject.gameapi.state.GameState;
import net.rankedproject.gameapi.state.GameStateBehavior;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.function.Consumer;

public class WaitingPlayersState implements GameState {

    @NotNull
    @Override
    public GameStateBehavior behavior() {
        return GameStateBehavior.builder()
                .startAction(gameStartAction())
                .duration(Duration.ofMinutes(2))
                .build();
    }

    @NotNull
    private Consumer<Game> gameStartAction() {
        return game -> {
            var mechanicContext = game.getMechanicContext();
            mechanicContext.enable(new NoPlayerDamageMechanic(game));
            mechanicContext.enable(new NoBreakBlockMechanic(game));
            mechanicContext.enable(new NoPlayerDamageMechanic(game));
        };
    }
}