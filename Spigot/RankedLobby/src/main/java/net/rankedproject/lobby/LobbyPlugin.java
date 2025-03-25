package net.rankedproject.lobby;

import net.rankedproject.common.rest.impl.RankedPlayerRestClient;
import net.rankedproject.common.rest.type.PlayerRestClient;
import net.rankedproject.lobby.listener.PlayerJoinListener;
import net.rankedproject.spigot.CommonPlugin;
import org.bukkit.event.Listener;

import java.util.Collection;
import java.util.List;

public class LobbyPlugin extends CommonPlugin {

    @Override
    public Collection<Listener> getBukkitListeners() {
        return List.of(new PlayerJoinListener());
    }

    @Override
    public Collection<Class<? extends PlayerRestClient<?>>> getRequiredPlayerData() {
        return List.of(RankedPlayerRestClient.class);
    }
}
