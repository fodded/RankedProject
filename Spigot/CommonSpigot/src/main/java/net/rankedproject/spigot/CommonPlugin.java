package net.rankedproject.spigot;

import com.google.common.base.Preconditions;
import com.google.inject.Injector;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.rankedproject.common.rest.provider.RestClientRegistry;
import net.rankedproject.spigot.guice.PluginBinderModule;
import net.rankedproject.spigot.instantiator.InstantiatorRegistry;
import net.rankedproject.spigot.instantiator.impl.SlimeLoaderInstantiator;
import net.rankedproject.spigot.registrar.BukkitListenerRegistrar;
import net.rankedproject.spigot.registrar.PluginRegistrar;
import net.rankedproject.spigot.server.RankedServer;
import org.bukkit.plugin.java.JavaPlugin;

@Slf4j
@Getter
public abstract class CommonPlugin extends JavaPlugin {

    private final RankedServer rankedServer = rankedServer();

    private InstantiatorRegistry instantiatorRegistry;
    private Injector injector;

    @Override
    public void onEnable() {
        initGuice();

        initRegistrars(rankedServer);
        initInstantiator(rankedServer);
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

    protected abstract RankedServer rankedServer();
}