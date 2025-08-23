package net.rankedproject.gameapi.state;

import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.mechanic.impl.NoPlayerDamageMechanic;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.function.Consumer;

public class TestGameState implements GameState {

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
            game.getMechanicContext().enable(new NoPlayerDamageMechanic(game));

            var schedulerContext = game.getSchedulerContext();
            schedulerContext.run((scheduler, plugin) -> scheduler.runTaskLater(plugin, () -> {

            }, 20));
        };
    }
}
