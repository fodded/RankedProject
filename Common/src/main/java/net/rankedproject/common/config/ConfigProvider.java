package net.rankedproject.common.config;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import net.rankedproject.common.config.accessor.ConfigAccessor;
import net.rankedproject.common.config.loader.ConfigLoader;
import net.rankedproject.common.config.search.ConfigSearchOption;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class ConfigProvider {

    private final ConfigLoader configLoader;
    private final ConfigAccessor configAccessor;

    @NotNull
    public <T extends Config> ConfigSearchOption.Builder get(Class<T> configType) {
        return ConfigSearchOption.builder()
                .configLoader(configLoader)
                .configAccessor(configAccessor)
                .config(configType);
    }

    @NotNull
    public CompletableFuture<Void> load(Class<? extends Config> configType) {
        return CompletableFuture.runAsync(() -> configLoader.load(null));
    }
}