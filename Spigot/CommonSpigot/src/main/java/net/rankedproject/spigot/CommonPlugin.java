package net.rankedproject.spigot;

import net.rankedproject.common.rest.type.PlayerRestClient;
import net.rankedproject.spigot.data.listener.PlayerDataLoadListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.List;

public abstract class CommonPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        registerListeners();
    }

    private void registerListeners() {
        registerBukkitListeners(List.of(new PlayerDataLoadListener(this)));
    }

    private void registerBukkitListeners(Collection<Listener> listeners) {
        PluginManager pluginManager = Bukkit.getPluginManager();

        listeners.forEach(listener -> pluginManager.registerEvents(listener, this));
        getBukkitListeners().forEach(listener -> pluginManager.registerEvents(listener, this));
    }

    public abstract Collection<Listener> getBukkitListeners();
    public abstract Collection<Class<? extends PlayerRestClient<?>>> getRequiredPlayerData();
}