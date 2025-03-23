package net.rankedproject.spigot.data.listener;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.rankedproject.common.rest.provider.RestProvider;
import net.rankedproject.spigot.CommonPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class PlayerDataLoadListener implements Listener {

    private final CommonPlugin plugin;

    @EventHandler
    public void onPlayerLogin(AsyncPlayerPreLoginEvent event) {
        UUID playerUUID = event.getUniqueId();
        CompletableFuture.allOf(plugin.getRequiredPlayerData()
                        .stream()
                        .map(data -> RestProvider.get(data).getAsync(playerUUID))
                        .toArray(CompletableFuture[]::new)
                )
                .thenRun(event::allow)
                .exceptionally(ex -> {
                    event.disallow(
                            AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                            MiniMessage.miniMessage().deserialize("<red>Failed to load your data")
                    );
                    return null;
                })
                .join();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(Component.empty());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.quitMessage(Component.empty());
    }
}
