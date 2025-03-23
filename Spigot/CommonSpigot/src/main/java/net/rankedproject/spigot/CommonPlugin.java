package net.rankedproject.spigot;

import net.rankedproject.common.data.domain.BasePlayer;
import net.rankedproject.common.rest.RestClient;
import net.rankedproject.spigot.data.listener.PlayerDataLoadListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public abstract class CommonPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        registerListeners();
    }

    private void registerListeners() {
        registerBukkitListeners(List.of(new PlayerDataLoadListener(this)));
    }

    private void registerBukkitListeners(Collection<Listener> listeners) {
        listeners.addAll(getBukkitListeners());
        listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    public abstract Collection<Listener> getBukkitListeners();
    public abstract Collection<Class<RestClient<UUID, BasePlayer>>> getRequiredPlayerData();
}