package net.rankedproject.common.rest.type;

import com.google.gson.JsonElement;
import net.rankedproject.common.data.domain.BasePlayer;
import net.rankedproject.common.rest.RestClient;
import okhttp3.Request;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class PlayerRestClient<V extends BasePlayer> extends RestClient<V> {
    public abstract V getPlayer(UUID key);
    public abstract Collection<V> getAllPlayers();
    public abstract void updatePlayer(V value);
    public abstract void savePlayer(V value);

    public CompletableFuture<V> getPlayerAsync(UUID playerUUID) {
        return async(() -> getPlayer(playerUUID));
    }

    public CompletableFuture<Collection<V>> getAllPlayersAsync(UUID playerUUID) {
        return async(this::getAllPlayers);
    }

    public CompletableFuture<Void> updatePlayerAsync(V data) {
        return async(() -> updatePlayer(data));
    }

    public CompletableFuture<Void> savePlayerAsync(V data) {
        return async(() -> savePlayer(data));
    }
}
