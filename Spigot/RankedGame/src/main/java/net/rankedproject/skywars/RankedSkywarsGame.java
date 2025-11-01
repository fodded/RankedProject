package net.rankedproject.skywars;

import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.metadata.GameMetadata;
import net.rankedproject.gameapi.state.GameState;
import net.rankedproject.skywars.state.GameWaitingState;
import net.rankedproject.spigot.CommonPlugin;
import org.jetbrains.annotations.NotNull;

public class RankedSkywarsGame extends Game {

    private static final GameWaitingState GAME_WAITING_STATE = new GameWaitingState();

    public RankedSkywarsGame(@NotNull CommonPlugin plugin, @NotNull GameMetadata metadata) {
        super(plugin, metadata);
    }

    @NotNull
    @Override
    public GameState getInitState() {
        return GAME_WAITING_STATE;
    }
}
