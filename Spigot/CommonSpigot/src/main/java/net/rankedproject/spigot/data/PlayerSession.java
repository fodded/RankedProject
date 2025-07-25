package net.rankedproject.spigot.data;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.rankedproject.common.data.domain.BasePlayer;
import net.rankedproject.common.rest.provider.RestProvider;
import net.rankedproject.common.rest.type.PlayerRestClient;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Getter
@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class PlayerSession {

    private final Map<UUID, Set<BasePlayer>> cache = new ConcurrentHashMap<>();
    private final RestProvider restProvider;

    /**
     * Loads data and saves to cache
     *
     * @param clients A Collection of @code{PlayerRestClient}
     * @param playerUUID UUID of the target player
     * @return CompletableFuture as Result
     */
    public CompletableFuture<?> load(
            Collection<Class<? extends PlayerRestClient<?>>> clients,
            UUID playerUUID
    ) {
        return CompletableFuture.allOf(clients.stream()
                .map(client -> restProvider.get(client)
                        .getPlayerAsync(playerUUID)
                        .thenAccept(this::updateCachedData)
                )
                .toArray(CompletableFuture[]::new));
    }

    public void unload(UUID playerUUID) {
        Set<BasePlayer> cachedData = cache.remove(playerUUID);
        cachedData.forEach(data -> {
            PlayerRestClient<BasePlayer> restClient = restProvider.getByReturnType(data.getClass());
            if (restClient == null) return;

            restClient.savePlayerAsync(data);
        });
    }

    public <T extends BasePlayer> CompletableFuture<Void> updateData(
            UUID playerUUID,
            Class<T> dataClassType,
            Consumer<T> dataConsumer
    ) {
        PlayerRestClient<T> restClient = restProvider.getByReturnType(dataClassType);
        Preconditions.checkArgument(restClient != null, "Attempted to save player's data with incorrect dataClassType");

        return restClient.getPlayerAsync(playerUUID)
                .thenCompose(retrievedData -> {
                    dataConsumer.accept(retrievedData);
                    updateCachedData(retrievedData);

                    return restClient.savePlayerAsync(retrievedData);
                });
    }

    public void updateCachedData(BasePlayer basePlayer) {
        UUID playerId = basePlayer.getId();
        Set<BasePlayer> existingCache = cache.getOrDefault(playerId, new HashSet<>());

        existingCache.removeIf(data -> data.getClass().isAssignableFrom(basePlayer.getClass()));
        existingCache.add(basePlayer);

        cache.put(playerId, existingCache);
    }

    public <T extends BasePlayer> T get(UUID playerUUID, Class<T> classType) {
        return classType.cast(cache.get(playerUUID)
                .stream()
                .filter(data -> data.getClass().isAssignableFrom(classType))
                .findFirst()
                .orElse(null));
    }
}