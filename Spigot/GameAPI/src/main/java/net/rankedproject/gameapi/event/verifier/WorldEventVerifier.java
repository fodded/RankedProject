package net.rankedproject.gameapi.event.verifier;

import net.rankedproject.gameapi.Game;
import org.bukkit.event.world.WorldEvent;

public class WorldEventVerifier implements GameEventVerifier<WorldEvent> {

    @Override
    public boolean isGameEvent(WorldEvent event, Game game) {
        var worldContext = game.getWorldContext();
        var world = worldContext.getWorld();

        if (world == null) return false;
        return world.getName().equals(event.getWorld().getName());
    }

    @Override
    public Class<? extends WorldEvent> getEventType() {
        return WorldEvent.class;
    }
}