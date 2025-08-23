package net.rankedproject.gameapi.event.handler;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public interface HandlerListProvider {

    /**
     * Returns HandlerList used to unregister events when they are not needed anymore.
     *
     * @param eventType Class instance of the event
     * @return HandlerList
     */
    @NotNull
    HandlerList get(@NotNull Class<? extends Event> eventType);
}
