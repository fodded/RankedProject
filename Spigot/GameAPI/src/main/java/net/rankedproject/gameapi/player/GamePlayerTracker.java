package net.rankedproject.gameapi.player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Predicate;

public class GamePlayerTracker {

    private final List<UUID> players = new ArrayList<>();

    public void addPlayer(@NotNull UUID playerUUID) {
        players.add(playerUUID);
    }

    public void removePlayer(@NotNull UUID playerUUID) {
        players.remove(playerUUID);
    }

    @NotNull
    @UnmodifiableView
    public Collection<UUID> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    @NotNull
    @UnmodifiableView
    public Collection<UUID> getPlayers(@NotNull Predicate<UUID> filter) {
        return players.stream()
                .filter(filter)
                .toList();
    }
}