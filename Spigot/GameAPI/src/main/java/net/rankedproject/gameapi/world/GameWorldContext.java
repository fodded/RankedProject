package net.rankedproject.gameapi.world;

import lombok.RequiredArgsConstructor;
import net.rankedproject.gameapi.Game;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.lang.ref.WeakReference;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class GameWorldContext {

    private static final WeakReference<World> NOT_LOADED_WORLD = new WeakReference<>(null);
    private final Game game;

    private WeakReference<World> world = NOT_LOADED_WORLD;

    public CompletableFuture<Void> load() {
        var worldData = game.getWorldData();

        var worldLoader = worldData.getWorldLoader().getLoader();
        var worldName = worldData.getWorldName();

        return worldLoader.load(game, worldName).thenAccept(loadedWorld -> {
            world = new WeakReference<>(loadedWorld);
        });
    }

    public void unload() {
        Bukkit.unloadWorld(getWorld(), false);
    }

    public World getWorld() {
        return world.get();
    }
}
