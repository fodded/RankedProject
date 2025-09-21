package net.rankedproject.gameapi.player;

import lombok.RequiredArgsConstructor;
import net.rankedproject.gameapi.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class GamePlayerStateContext {

    private final Map<UUID, GamePlayerState> playerStates = new HashMap<>();
    private final Game game;

    public void apply(@NotNull UUID playerUUID, @NotNull GamePlayerState playerState) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return;

        playerState.apply(player);
    }
}