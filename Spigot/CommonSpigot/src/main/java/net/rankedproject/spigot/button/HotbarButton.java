package net.rankedproject.spigot.button;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface HotbarButton {

    void onClick(@NotNull Player player);
}
