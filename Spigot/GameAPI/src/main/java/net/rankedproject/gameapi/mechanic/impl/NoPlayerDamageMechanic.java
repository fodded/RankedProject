package net.rankedproject.gameapi.mechanic.impl;

import lombok.RequiredArgsConstructor;
import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.event.GameEventListenerData;
import net.rankedproject.gameapi.mechanic.GameMechanic;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

@RequiredArgsConstructor
public class NoPlayerDamageMechanic implements GameMechanic {

    private static final String EVENT_ID = "nodamage-mechanic-event";
    private final Game game;

    @Override
    public void enable() {
        var damageEvent = GameEventListenerData.<EntityDamageEvent>builder()
                .create(EVENT_ID, EntityDamageEvent.class)
                .filter(event -> event.getEntity() instanceof Player)
                .on(event -> event.setCancelled(true))
                .build();

        var eventContext = game.getEventContext();
        eventContext.register(damageEvent);
    }

    @Override
    public void disable() {
        game.getEventContext().unregister(EVENT_ID);
    }
}
