package net.rankedproject.gameapi.state;

import org.jetbrains.annotations.NotNull;

public interface GameState {

    /**
     * Determines the behaviour of the game during the given game state
     *
     * @return GameStateBehavior object with defined rules
     */
    @NotNull
    GameStateBehavior behavior();
}
