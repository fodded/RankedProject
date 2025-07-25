package net.rankedproject.spigot;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.Getter;
import net.rankedproject.common.registry.RegistryProvider;
import net.rankedproject.common.rest.provider.RestClientRegistry;
import net.rankedproject.spigot.guice.PluginBinderModule;
import net.rankedproject.spigot.registrar.BukkitListenerRegistrar;
import net.rankedproject.spigot.registrar.PluginRegistrar;
import net.rankedproject.spigot.server.RankedServer;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class CommonPlugin extends JavaPlugin {

    @Inject
    private RegistryProvider registryProvider;

    @Getter
    private Injector injector;

    @Override
    public void onEnable() {
        initGuice();
        initRegistrars();
    }

    @Override
    public void onDisable() {
        RestClientRegistry registry = registryProvider.getRegistry(RestClientRegistry.class);
        registry.getAllRegistered().forEach((type, client) -> client.shutdown());
    }

    private void initGuice() {
        PluginBinderModule module = new PluginBinderModule(this);
        injector = module.createInjector();
        injector.injectMembers(this);
    }

    private void initRegistrars() {
        var rankedServer = getRankedServer();
        rankedServer.registrars().forEach(PluginRegistrar::register);

        var bukkitListenerRegistrar = new BukkitListenerRegistrar(this);
        bukkitListenerRegistrar.register();
    }

    @Provides
    @Singleton
    public abstract RankedServer getRankedServer();
}