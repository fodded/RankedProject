package net.rankedproject.common.config.accessor;

import com.google.inject.ImplementedBy;
import net.rankedproject.common.config.Config;
import net.rankedproject.common.config.loader.ConfigLoader;
import net.rankedproject.common.rest.RestClient;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@ImplementedBy(CachedConfigAccessor.class)
public interface ConfigAccessor {

    @NotNull
    <T extends Config> T get(@NotNull Class<T> type, @NotNull ConfigLoader loader);

    @NotNull
    default CompletableFuture<Void> loadAll(@NotNull Collection<? extends Class<? extends Config>> types, @NotNull ConfigLoader loader) {
        return CompletableFuture.allOf(types.stream()
                .map(type -> CompletableFuture.supplyAsync(() -> get(type, loader), RestClient.EXECUTOR_SERVICE))
                .toArray(CompletableFuture[]::new));
    }
}
