package net.rankedproject.gameapi.event;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import net.rankedproject.spigot.CommonPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class GameEventHandler {

    @Inject
    private final CommonPlugin plugin;

    public <E extends Event> void register(
            @NotNull Class<E> eventType,
            @NotNull Consumer<E> eventHandler
    ) {
        Bukkit.getPluginManager().registerEvent(
                eventType,
                new Listener() {},
                EventPriority.NORMAL,
                (listener, event) -> {
                    if (event.getClass().equals(eventType)) {
                        eventHandler.accept(eventType.cast(event));
                    }
                },
                plugin
        );
    }
}
