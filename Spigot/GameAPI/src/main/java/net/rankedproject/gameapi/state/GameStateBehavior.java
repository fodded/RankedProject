package net.rankedproject.gameapi.state;

import lombok.Builder;
import lombok.Getter;
import net.rankedproject.gameapi.Game;

import java.time.Duration;
import java.util.function.Consumer;

@Getter
@Builder
public class GameStateBehavior {

    private final Consumer<Game> startAction, stopAction;

    @Builder.Default
    private final Duration duration = Duration.ofMinutes(1);
}