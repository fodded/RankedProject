package net.rankedproject.gameapi.event.verifier;

import net.rankedproject.gameapi.Game;
import org.bukkit.entity.Entity;
import org.bukkit.event.inventory.InventoryEvent;

public class InventoryEventVerifier implements GameEventVerifier<InventoryEvent> {

    @Override
    public boolean isGameEvent(InventoryEvent event, Game game) {
        var viewers = event.getViewers();
        var viewerUuids = viewers.stream()
                .map(Entity::getUniqueId)
                .toList();

        var playerTracker = game.getPlayerTracker();
        var gamePlayers = playerTracker.getPlayers();

        return gamePlayers.containsAll(viewerUuids);
    }

    @Override
    public Class<? extends InventoryEvent> getEventType() {
        return InventoryEvent.class;
    }
}
