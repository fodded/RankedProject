package net.rankedproject.common.config.accessor;

import net.rankedproject.common.config.Config;
import net.rankedproject.common.config.loader.ConfigLoader;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CachedConfigAccessor implements ConfigAccessor {

    private final Map<String, Config> loadedConfigs = new ConcurrentHashMap<>();

    @NotNull
    @Override
    public <T extends Config> T get(@NotNull Class<T> configType, @NotNull ConfigLoader configLoader) {
        return configType.cast(loadedConfigs.computeIfAbsent(configType.getSimpleName(), configLoader::load));
    }
}