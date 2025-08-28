package net.rankedproject.gameapi.player;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface GamePlayerState {

    /**
     * Applies changes to player's game object
     *
     * @param player Game player object instance
     */
    void apply(@NotNull Player player);
}
