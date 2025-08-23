package net.rankedproject.gameapi.event;

import lombok.RequiredArgsConstructor;
import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.event.handler.CachedHandlerListProvider;
import net.rankedproject.gameapi.event.handler.HandlerListProvider;
import net.rankedproject.spigot.CommonPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class GameEventContext {

    private final Game game;
    private final CommonPlugin plugin;

    private final HandlerListProvider handlerProvider = new CachedHandlerListProvider();
    private final Map<String, GameEventListener<?>> listeners = new ConcurrentHashMap<>();

    public <E extends Event> void register(@NotNull GameEventListenerData<E> listenerData) {
        var eventType = listenerData.eventType();
        var eventListener = new GameEventListener<>(game, handlerProvider, listenerData);

        listeners.put(listenerData.id(), eventListener);
        Bukkit.getPluginManager().registerEvent(eventType, eventListener, EventPriority.NORMAL, eventListener, plugin);
    }

    public void unregister(@NotNull String eventId) {
        var gameEventListener = listeners.remove(eventId);
        if (gameEventListener == null) return;
        
        var gameEventListenerConsumer = unregisterGameListener();
        gameEventListenerConsumer.accept(gameEventListener);
    }
    
    public void unregisterByType(@NotNull Class<? extends Event> eventType) {
        listeners.values().stream()
                .filter(listener -> eventType == listener.getMetadata().eventType())
                .findFirst()
                .ifPresent(unregisterGameListener());
    }

    public void unregisterByTypes(@NotNull Collection<? extends Class<? extends Event>> eventTypes) {
        listeners.values().stream()
                .filter(listener -> eventTypes.contains(listener.getMetadata().eventType()))
                .forEach(unregisterGameListener());
    }

    public void unregisterAll() {
        listeners.values().forEach(unregisterGameListener());
    }

    @NotNull
    private Consumer<GameEventListener<?>> unregisterGameListener() {
        return listener -> {
            listener.setRegistered(true);

            var eventMetadata = listener.getMetadata();
            var eventType = eventMetadata.eventType();

            var handlerList = handlerProvider.get(eventType);
            handlerList.unregister(listener);

            listeners.remove(eventMetadata.id());
        };
    }
}
