package net.rankedproject.gameapi.world;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public interface WorldLoader {

    @NotNull
    World load();
}
