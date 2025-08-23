package net.rankedproject.gameapi.world.loader;

import com.google.common.base.Preconditions;
import com.infernalsuite.asp.api.AdvancedSlimePaperAPI;
import com.infernalsuite.asp.api.loaders.SlimeLoader;
import com.infernalsuite.asp.api.world.SlimeWorld;
import com.infernalsuite.asp.api.world.properties.SlimePropertyMap;
import lombok.SneakyThrows;
import net.minecraft.server.MinecraftServer;
import net.rankedproject.common.rest.RestCrudAPI;
import net.rankedproject.gameapi.Game;
import net.rankedproject.spigot.loader.SlimeLoaderInstantiator;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class SlimeWorldLoader implements WorldLoader {

    @NotNull
    @Override
    public CompletableFuture<World> load(@NotNull Game game, @NotNull String worldName) {
        var plugin = game.getPlugin();
        var loaderRegistry = plugin.getLoaderRegistry();

        var slimeLoaderInstantiator = loaderRegistry.get(SlimeLoaderInstantiator.class);
        Preconditions.checkNotNull(slimeLoaderInstantiator);

        var slimeLoader = slimeLoaderInstantiator.get();
        var slimePaper = AdvancedSlimePaperAPI.instance();

        return CompletableFuture
                .supplyAsync(() -> readSlimeWorld(worldName, slimePaper, slimeLoader), RestCrudAPI.EXECUTOR_SERVICE)
                .thenApplyAsync(slimeWorld -> {
                    var loadedWorld = slimePaper.loadWorld(slimeWorld, true);
                    return loadedWorld.getBukkitWorld();
                }, MinecraftServer.getServer().executor);
    }

    @SneakyThrows
    private SlimeWorld readSlimeWorld(@NotNull String worldName, AdvancedSlimePaperAPI slimePaper, SlimeLoader slimeLoader) {
        return slimePaper.readWorld(slimeLoader, worldName, false, new SlimePropertyMap());
    }
}