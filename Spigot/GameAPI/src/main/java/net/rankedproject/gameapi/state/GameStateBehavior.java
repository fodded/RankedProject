package net.rankedproject.gameapi.state;

import lombok.Builder;
import lombok.Getter;
import net.rankedproject.gameapi.Game;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.function.Consumer;

@Getter
@Builder
public class GameStateBehavior {

    /**
     * startAction is executed when the state starts
     */
    @NotNull
    private final Consumer<Game> startAction;

    /**
     * stopAction is executed when the state stops
     * It may never be executed automatically, if duration is null
     */
    @Nullable
    private final Consumer<Game> stopAction;

    /**
     * It determines whether there should be a next state
     * If the nextState is null, then the game will stop once if state completes
     */
    @Nullable
    private final GameState nextState;

    /**
     * Duration is used to determine when to switch to the next state
     * If the duration is null, then stopAction() consumer will never be called.
     */
    @Nullable
    private final Duration duration;
}