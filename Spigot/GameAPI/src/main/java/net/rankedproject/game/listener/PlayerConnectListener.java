package net.rankedproject.game.listener;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.MinecraftServer;
import net.rankedproject.game.finder.GameFinder;
import net.rankedproject.game.tracker.GameTracker;
import net.rankedproject.gameapi.event.type.GamePlayerJoinEvent;
import net.rankedproject.gameapi.event.type.GamePlayerQuitEvent;
import net.rankedproject.spigot.logger.BukkitLogger;
import net.rankedproject.spigot.util.ComponentUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class PlayerConnectListener implements Listener {

    private static final String ERROR_GAME_MESSAGE = "<red>Couldn't find a game for you";
    private static final String ERROR_GAME_LOG = "Couldn't find a game for player %s in under 3 seconds. An exception was thrown: %s";

    private final BukkitLogger logger;

    private final GameTracker gameTracker;
    private final GameFinder<?> gameFinder;

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        var playerUUID = event.getUniqueId();

        try {
            gameFinder.find(playerUUID).get(3, TimeUnit.SECONDS);
            event.allow();
        } catch (TimeoutException | ExecutionException | InterruptedException exception) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ComponentUtil.deserialize(ERROR_GAME_MESSAGE));
            logger.warning(ERROR_GAME_LOG.formatted(playerUUID, exception.getLocalizedMessage()));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        var eventPlayer = event.getPlayer();
        var playerUUID = eventPlayer.getUniqueId();

        gameFinder.find(playerUUID)
                .orTimeout(1, TimeUnit.SECONDS)
                .whenCompleteAsync((game, throwable) -> {
                    var player = Bukkit.getPlayer(playerUUID);
                    if (player == null) {
                        return;
                    }

                    if (throwable != null) {
                        player.kick(ComponentUtil.deserialize(ERROR_GAME_MESSAGE));
                        return;
                    }

                    var playerTracker = game.getPlayerTracker();
                    playerTracker.addPlayer(playerUUID);

                    var playerJoinEvent = new GamePlayerJoinEvent(game, player);
                    Bukkit.getPluginManager().callEvent(playerJoinEvent);
                }, MinecraftServer.getServer().executor);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        var player = event.getPlayer();
        var playerUUID = player.getUniqueId();

        var game = gameTracker.getGames().stream()
                .filter(filteredGame -> filteredGame.getPlayerTracker().getPlayers().contains(playerUUID))
                .findFirst()
                .orElse(null);

        if (game == null) {
            return;
        }

        var playerTracker = game.getPlayerTracker();
        playerTracker.removePlayer(playerUUID);

        var playerQuitEvent = new GamePlayerQuitEvent(game, player);
        Bukkit.getPluginManager().callEvent(playerQuitEvent);
    }
}