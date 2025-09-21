package net.rankedproject.game.finder;

import com.google.inject.ImplementedBy;
import net.rankedproject.gameapi.Game;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@ImplementedBy(RandomGameFinder.class)
public interface GameFinder<G extends Game> {

    /**
     * Finds existing waiting to start game or creates a new one
     *
     * @param playerUUID UUID of the player looking for a game
     * @return Game
     */
    @NotNull
    CompletableFuture<G> find(@NotNull UUID playerUUID);

    /**
     * Finds random game's identifier
     *
     * @return Game identifier
     */
    // TODO: Make it search a random game from config's list
    @NotNull
    default String findRandomGameIdentifier() {
        return "Game";
    }
}
