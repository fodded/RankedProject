package net.rankedproject.spigot;

import com.google.common.base.Preconditions;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.rankedproject.common.config.ConfigProvider;
import net.rankedproject.common.rest.provider.RestClientRegistry;
import net.rankedproject.spigot.guice.PluginBinderModule;
import net.rankedproject.spigot.instantiator.InstantiatorRegistry;
import net.rankedproject.spigot.instantiator.impl.SlimeLoaderInstantiator;
import net.rankedproject.spigot.registrar.BukkitListenerRegistrar;
import net.rankedproject.spigot.registrar.PluginRegistrar;
import net.rankedproject.spigot.server.RankedServer;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

@Slf4j
public abstract class CommonPlugin extends JavaPlugin {

    @Getter
    private InstantiatorRegistry instantiatorRegistry;

    @Getter
    private Injector injector;

    @Override
    public void onEnable() {
        initGuice();

        var rankedServer = getRankedServer();
        initRegistrars(rankedServer);
        initInstantiator(rankedServer);

        Location location = ConfigProvider.get(TestConfig.class, injector)
                .path("bowi.three")
                .placeholder("player-name", "bowi")
                .get();
    }

    @Override
    public void onDisable() {
        injector.getInstance(RestClientRegistry.class)
                .getAllRegistered()
                .forEach((type, client) -> client.shutdown());
    }

    private void initGuice() {
        PluginBinderModule module = new PluginBinderModule(this);
        injector = module.createInjector();
        injector.injectMembers(this);
    }

    private void initRegistrars(RankedServer rankedServer) {
        var registrars = rankedServer.registrars();
        registrars.forEach(PluginRegistrar::register);

        var bukkitListenerRegistrar = new BukkitListenerRegistrar(this);
        bukkitListenerRegistrar.register();
    }

    @SuppressWarnings("unchecked")
    private void initInstantiator(RankedServer rankedServer) {
        instantiatorRegistry = new InstantiatorRegistry();
        instantiatorRegistry.register(SlimeLoaderInstantiator.class, new SlimeLoaderInstantiator());

        var instantiator = rankedServer.instantiator();
        instantiator.forEach(loader -> instantiatorRegistry.register(loader.getClass(), loader));

        var registeredInstantiators = instantiatorRegistry.getAll();
        registeredInstantiators.forEach(loader -> {
            var loadedData = loader.init();
            Preconditions.checkNotNull(loadedData);
        });
    }

    @Provides
    @Singleton
    public abstract RankedServer getRankedServer();
}