package net.rankedproject.spigot.data;

import com.google.common.base.Preconditions;
import lombok.Getter;
import net.rankedproject.common.data.domain.BasePlayer;
import net.rankedproject.common.rest.provider.RestProvider;
import net.rankedproject.common.rest.type.PlayerRestClient;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Getter
public class PlayerSession {

    @Getter
    private static final PlayerSession instance = new PlayerSession();

    private final Map<UUID, Set<BasePlayer>> cache = new ConcurrentHashMap<>();

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
                .map(client -> RestProvider.get(client)
                        .getPlayerAsync(playerUUID)
                        .thenAccept(data -> {
                            Set<BasePlayer> existingCache = cache.getOrDefault(playerUUID, new HashSet<>());
                            existingCache.add(data);

                            cache.put(playerUUID, existingCache);
                        })
                )
                .toArray(CompletableFuture[]::new));
    }

    public void unload(UUID playerUUID) {
        Set<BasePlayer> cachedData = cache.remove(playerUUID);
        cachedData.forEach(data -> {
            PlayerRestClient<BasePlayer> restClient = RestProvider.getByReturnType(data.getClass());
            if (restClient == null) return;

            restClient.savePlayerAsync(data);
        });
    }

    public <T extends BasePlayer> T getCachedData(UUID playerUUID, Class<T> classType) {
        return classType.cast(cache.get(playerUUID)
                .stream()
                .filter(data -> data.getClass().isAssignableFrom(classType))
                .findFirst()
                .orElse(null)
        );
    }

    public <T extends BasePlayer> CompletableFuture<Void> updateData(
            UUID playerUUID,
            Class<T> dataClassType,
            Consumer<T> dataConsumer
    ) {
        T data = getCachedData(playerUUID, dataClassType);
        if (data != null) {
            dataConsumer.accept(data);
            return CompletableFuture.completedFuture(null);
        }

        PlayerRestClient<T> restClient = RestProvider.getByReturnType(dataClassType);
        Preconditions.checkArgument(restClient != null, "Attempted to save player's data with incorrect dataClassType");

        return restClient.getPlayerAsync(playerUUID)
                .thenCompose(retrievedData -> {
                    dataConsumer.accept(retrievedData);
                    return restClient.savePlayerAsync(retrievedData);
                });
    }

    public static <T extends BasePlayer> T get(UUID playerUUID, Class<T> classType) {
       return classType.cast(getInstance().getCachedData(playerUUID, classType));
    }
}