package net.rankedproject.gameapi.event.verifier;

import net.rankedproject.gameapi.Game;
import org.bukkit.event.player.PlayerEvent;

public class PlayerEventVerifier implements GameEventVerifier<PlayerEvent> {

    @Override
    public boolean isGameEvent(PlayerEvent event, Game game) {
        var player = event.getPlayer();
        var playerTracker = game.getPlayerTracker();
        var gamePlayers = playerTracker.getPlayers();

        return gamePlayers.contains(player.getUniqueId());
    }

    @Override
    public Class<? extends PlayerEvent> getEventType() {
        return PlayerEvent.class;
    }
}