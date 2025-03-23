package net.rankedproject.spigot.data;

import lombok.Getter;
import net.rankedproject.common.data.domain.BasePlayer;
import net.rankedproject.common.rest.RestClient;
import net.rankedproject.common.rest.provider.RestProvider;
import net.rankedproject.common.rest.type.PlayerRestClient;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

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
    public <T extends BasePlayer> CompletableFuture<T> load(
            Collection<Class<? extends PlayerRestClient<T>>> clients,
            UUID playerUUID
    ) {
        CompletableFuture<T> future = new CompletableFuture<>();
        clients.forEach(client -> future
                .thenCompose($ -> RestProvider.get(client).getPlayerAsync(playerUUID))
                .thenAccept(data -> {
                    Set<BasePlayer> existingCache = cache.getOrDefault(playerUUID, new HashSet<>());
                    existingCache.add(data);

                    cache.put(playerUUID, existingCache);
                })
        );

        return future;
    }

    public void unload(UUID playerUUID) {
        Set< BasePlayer> cachedData = cache.remove(playerUUID);
        cachedData.forEach(data -> {
            PlayerRestClient<? extends BasePlayer> restClient = null;
            // TODO: save data on player quit event
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

    public static <T extends BasePlayer> T get(UUID playerUUID, Class<T> classType) {
       return classType.cast(getInstance().getCachedData(playerUUID, classType));
    }
}
