package net.rankedproject.game.tracker;

import com.google.inject.Singleton;
import net.rankedproject.gameapi.Game;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
public class GameTracker {

    private final List<Game> games = new ArrayList<>();

    public void track(@NotNull Game game) {
        this.games.add(game);
    }

    public void untrack(@NotNull Game game) {
        this.games.remove(game);
    }

    @NotNull
    @UnmodifiableView
    public List<Game> getGames() {
        return Collections.unmodifiableList(games);
    }
}