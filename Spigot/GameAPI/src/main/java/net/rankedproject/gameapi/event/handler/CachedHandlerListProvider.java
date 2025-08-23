package net.rankedproject.gameapi.event.handler;

import lombok.SneakyThrows;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CachedHandlerListProvider implements HandlerListProvider {

    private final Map<Class<? extends Event>, HandlerList> cachedHandlers = new ConcurrentHashMap<>();

    @Override
    @NotNull
    public HandlerList get(@NotNull Class<? extends Event> eventType) {
        return cachedHandlers.computeIfAbsent(eventType, this::getFromEventClass);
    }

    @SneakyThrows
    @NotNull
    private HandlerList getFromEventClass(@NotNull Class<? extends Event> eventType) {
        Method method_getHandlerList = eventType.getMethod("getHandlerList");
        return (HandlerList) method_getHandlerList.invoke(null);
    }
}
