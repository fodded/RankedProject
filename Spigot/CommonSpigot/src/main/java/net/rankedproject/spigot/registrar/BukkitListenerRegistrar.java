package net.rankedproject.spigot.registrar;

import lombok.RequiredArgsConstructor;
import net.rankedproject.spigot.CommonPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.reflections.Reflections;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class BukkitListenerRegistrar implements PluginRegistrar {

    private static final String PACKAGE_LOOKUP_NAME = "net.rankedproject";

    private final CommonPlugin plugin;

    @Override
    public CompletableFuture<?> register() {
        var injector = plugin.getInjector();
        var pluginManager = Bukkit.getPluginManager();

        var reflections = new Reflections(PACKAGE_LOOKUP_NAME);
        reflections.getSubTypesOf(Listener.class).forEach(classType -> {
            var listener = injector.getInstance(classType);
            if (listener instanceof EventExecutor) return;

            pluginManager.registerEvents(listener, plugin);
        });

        return CompletableFuture.completedFuture(null);
    }
}