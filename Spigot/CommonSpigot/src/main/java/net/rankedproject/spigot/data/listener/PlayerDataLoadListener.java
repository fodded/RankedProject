package net.rankedproject.spigot.data.listener;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.rankedproject.spigot.CommonPlugin;
import net.rankedproject.spigot.data.PlayerSessionImpl;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

@RequiredArgsConstructor(onConstructor_={@Inject})
public class PlayerDataLoadListener implements Listener {

    private final CommonPlugin plugin;
    private final PlayerSessionImpl playerSession;

    @EventHandler
    public void onPlayerLogin(AsyncPlayerPreLoginEvent event) {
        UUID playerUUID = event.getUniqueId();
        playerSession.load(plugin.getRankedServer().requiredPlayerData(), playerUUID)
                .thenRun(event::allow)
                .exceptionally(ex -> {
                    event.disallow(
                            AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                            MiniMessage.miniMessage().deserialize("<red>Failed to load your data")
                    );
                    plugin.getLogger().severe("Failed to load data for " + playerUUID + " " + ex.getMessage());
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

        UUID playerUUID = event.getPlayer().getUniqueId();
        playerSession.unload(playerUUID);
    }
}
