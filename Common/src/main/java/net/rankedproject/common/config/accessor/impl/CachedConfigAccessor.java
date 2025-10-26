package net.rankedproject.common.config.accessor.impl;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;
import net.rankedproject.common.config.Config;
import net.rankedproject.common.config.accessor.ConfigAccessor;
import net.rankedproject.common.config.parser.ParsedConfig;
import net.rankedproject.common.config.reader.ConfigReadOption;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class CachedConfigAccessor implements ConfigAccessor {

    private final Map<Class<? extends Config>, ParsedConfig<?>> loadedConfigs = new ConcurrentHashMap<>();

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public <T> ParsedConfig<T> get(@NotNull ConfigReadOption readOption)  {
        var configType = readOption.configType();
        var parsedConfig = (ParsedConfig<T>) loadedConfigs.get(configType);

        Preconditions.checkNotNull(parsedConfig, "Config %s wasn't loaded".formatted(configType));
        return parsedConfig;
    }

    @Override
    public <T extends Config> ParsedConfig<?> load(@NotNull T config) {
        var configName = config.getName();
        var loader = config.getConfigLoader();
        var loadedConfig = loader.load(configName);

        var parser = config.getParser();
        var parsedConfig = parser.parse(loadedConfig);

        loadedConfigs.put(config.getClass(), parsedConfig);
        return parsedConfig;
    }
}