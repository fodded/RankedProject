package net.rankedproject.game.tracker;

import com.google.inject.Singleton;
import net.rankedproject.gameapi.Game;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Singleton
public class GameTracker {

    private final Set<Game> games = new TreeSet<>(Comparator.comparingLong(game -> game.getPlayerTracker().getPlayers().size()));

    public void track(@NotNull Game game) {
        this.games.add(game);
    }

    public void untrack(@NotNull Game game) {
        this.games.remove(game);
    }

    @NotNull
    @UnmodifiableView
    public Set<Game> getGames() {
        return Collections.unmodifiableSet(games);
    }
}