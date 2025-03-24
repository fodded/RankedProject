package net.rankedproject.common.rest.type;

import net.rankedproject.common.data.domain.BasePlayer;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnusedReturnValue")
public abstract class PlayerRestClient<V extends BasePlayer> extends CrudRestClient<V> {

    public Collection<V> getAllPlayers() {
        return getAll();
    }

    public V getPlayer(UUID key) {
        return get(key.toString());
    }

    public void updatePlayer(V value) {
        put(value);
    }

    public void savePlayer(V value) {
        post(value);
    }

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
