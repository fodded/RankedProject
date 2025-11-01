package net.rankedproject.gameapi.config;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;
import net.rankedproject.common.config.Config;
import net.rankedproject.common.config.ConfigMetadata;
import net.rankedproject.spigot.config.BukkitConfigLoader;
import net.rankedproject.spigot.config.BukkitConfigParser;
import org.jetbrains.annotations.NotNull;

@Singleton
@RequiredArgsConstructor(onConstructor_={@Inject})
public class MapInfoConfig implements Config {

    private final Injector injector;

    @NotNull
    @Override
    public ConfigMetadata getMetadata() {
        return ConfigMetadata.builder()
                .name("map-info.yml")
                .loader(injector.getInstance(BukkitConfigLoader.class))
                .parser(injector.getInstance(BukkitConfigParser.class))
                .build();
    }
}