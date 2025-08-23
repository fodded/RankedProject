package net.rankedproject.gameapi.world.loader;

import net.rankedproject.gameapi.Game;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface WorldLoader {

    @NotNull
    CompletableFuture<World> load(@NotNull Game game, @NotNull String worldName);
}
