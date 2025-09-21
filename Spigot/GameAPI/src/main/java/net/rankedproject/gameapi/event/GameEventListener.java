package net.rankedproject.gameapi.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.event.handler.HandlerListProvider;
import net.rankedproject.gameapi.event.verifier.GameEventVerifier;
import net.rankedproject.gameapi.event.verifier.impl.*;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GameEventListener<T extends Event> implements Listener, EventExecutor {

    private static final List<GameEventVerifier<?>> GAME_EVENT_VERIFIERS = List.of(
            new EntityEventVerifier(),
            new WorldEventVerifier(),
            new InventoryEventVerifier(),
            new PlayerEventVerifier(),
            new VehicleEventVerifier(),
            new WeatherEventVerifier()
    );

    private final Game game;
    private final HandlerListProvider handlerProvider;
    private final GameEventListenerData<T> metadata;

    @Setter
    private boolean registered = true;

    @Override
    @SuppressWarnings("unchecked")
    public void execute(@NotNull Listener listener, @NotNull Event event) {
        var eventType = metadata.eventType();
        if (event.getClass() != eventType) {
            return;
        }

        if (!registered) {
            HandlerList handlerList = handlerProvider.get(event.getClass());
            handlerList.unregister(listener);
            return;
        }

        T castedEvent = eventType.cast(event);
        var eventFilters = metadata.filters();

        boolean passedFilters = eventFilters.stream().allMatch(filter -> filter.test(castedEvent));
        if (!passedFilters) {
            return;
        }

        List<GameEventVerifier<T>> verifiers = GAME_EVENT_VERIFIERS.stream()
                .filter(verifier -> verifier.getEventType().isAssignableFrom(castedEvent.getClass()))
                .map(verifier -> (GameEventVerifier<T>) verifier)
                .toList();

        boolean passedVerifiers = verifiers.stream().allMatch(verifier -> verifier.isGameEvent(castedEvent, game));
        if (!passedVerifiers) {
            return;
        }

        var eventConsumer = metadata.eventAction();
        eventConsumer.accept(castedEvent);
    }
}
