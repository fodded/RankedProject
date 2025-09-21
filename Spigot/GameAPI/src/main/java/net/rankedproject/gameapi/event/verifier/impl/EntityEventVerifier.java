package net.rankedproject.gameapi.event.verifier.impl;

import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.event.verifier.GameEventVerifier;
import org.bukkit.event.entity.EntityEvent;

public class EntityEventVerifier implements GameEventVerifier<EntityEvent> {

    @Override
    public boolean isGameEvent(EntityEvent event, Game game) {
        var worldContext = game.getWorldContext();
        var world = worldContext.getWorld();

        if (world == null) return false;
        return world.getEntities().contains(event.getEntity());
    }

    @Override
    public Class<? extends EntityEvent> getEventType() {
        return EntityEvent.class;
    }
}