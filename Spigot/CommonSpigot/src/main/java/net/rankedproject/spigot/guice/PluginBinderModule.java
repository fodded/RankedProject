package net.rankedproject.spigot.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.RequiredArgsConstructor;
import net.rankedproject.common.config.loader.ConfigLoader;
import net.rankedproject.spigot.CommonPlugin;
import net.rankedproject.spigot.config.BukkitConfigLoader;

@RequiredArgsConstructor
public class PluginBinderModule extends AbstractModule {

    private final CommonPlugin plugin;

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        bind(CommonPlugin.class).toInstance(plugin);
        bind(ConfigLoader.class).toInstance(new BukkitConfigLoader(plugin));
    }
}