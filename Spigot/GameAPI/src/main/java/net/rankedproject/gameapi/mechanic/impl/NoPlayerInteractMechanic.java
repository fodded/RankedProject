package net.rankedproject.gameapi.mechanic.impl;

import lombok.RequiredArgsConstructor;
import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.event.GameEventListenerData;
import net.rankedproject.gameapi.mechanic.GameMechanic;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class NoPlayerInteractMechanic implements GameMechanic {

    private static final String EVENT_ID = "nointeract-mechanic-event";
    private final Game game;

    @Override
    public void enable() {
        var interactEvent = GameEventListenerData.<PlayerInteractEvent>builder()
                .create(EVENT_ID, PlayerInteractEvent.class)
                .filter(event -> {
                    var location = event.getInteractionPoint();
                    if (location == null) return false;

                    var world = location.getWorld();
                    if (world == null) return false;

                    var gameWorldContext = game.getWorldContext();
                    var gameWorld = gameWorldContext.getWorld();
                    if (gameWorld == null) return false;

                    return gameWorld.getName().equals(world.getName());
                })
                .on(event -> event.setCancelled(true))
                .build();

        var eventContext = game.getEventContext();
        eventContext.register(interactEvent);
    }

    @Override
    public void disable() {
        game.getEventContext().unregister(EVENT_ID);
    }
}
