package net.rankedproject.spigot.registrar;

import lombok.RequiredArgsConstructor;
import net.rankedproject.common.config.ConfigProvider;
import net.rankedproject.spigot.CommonPlugin;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class ConfigRegistrar implements PluginRegistrar {

    private final CommonPlugin plugin;

    @Override
    public CompletableFuture<?> register() {
        var injector = plugin.getInjector();
        var rankedServer = plugin.getRankedServer();

        var configs = rankedServer.configs();
        return ConfigProvider.loadAll(configs, injector);
    }
}