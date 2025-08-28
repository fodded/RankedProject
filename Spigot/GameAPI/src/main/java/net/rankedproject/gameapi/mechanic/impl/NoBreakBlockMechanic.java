package net.rankedproject.gameapi.mechanic.impl;

import lombok.RequiredArgsConstructor;
import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.event.GameEventListenerData;
import net.rankedproject.gameapi.mechanic.GameMechanic;
import org.bukkit.event.block.BlockBreakEvent;

@RequiredArgsConstructor
public class NoBreakBlockMechanic implements GameMechanic {

    private static final String EVENT_ID = "nobreakblock-mechanic-event";
    private final Game game;

    @Override
    public void enable() {
        var breakBlockEvent = GameEventListenerData.<BlockBreakEvent>builder()
                .create(EVENT_ID, BlockBreakEvent.class)
                .on(event -> event.setCancelled(true))
                .build();

        var eventContext = game.getEventContext();
        eventContext.register(breakBlockEvent);
    }

    @Override
    public void disable() {
        game.getEventContext().unregister(EVENT_ID);
    }
}
