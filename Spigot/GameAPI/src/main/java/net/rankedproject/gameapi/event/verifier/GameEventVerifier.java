package net.rankedproject.gameapi.event.verifier;

import net.rankedproject.gameapi.Game;
import org.bukkit.event.Event;

public interface GameEventVerifier<T extends Event> {

    /**
     * Verifies the event is a part of the game.
     * Checks that entity or world are the same as game's one
     *
     * @param game Game instance object
     * @return true if event is part of the game, false if otherwise
     */
    boolean isGameEvent(T event, Game game);

    Class<? extends T> getEventType();
}
