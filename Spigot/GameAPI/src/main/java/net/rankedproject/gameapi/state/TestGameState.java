package net.rankedproject.gameapi.state;

import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.mechanic.impl.NoBreakBlockMechanic;
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
                .stopAction()
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

            var schedulerContext = game.getSchedulerContext();
            schedulerContext.run((scheduler, plugin) -> scheduler.runTaskLater(plugin, () -> {

            }, 20));
        };
    }

    @NotNull
    private Consumer<Game> gameStopAction() {
        return game -> {
            var eventContext = game.getEventContext();

        };
    }
}
