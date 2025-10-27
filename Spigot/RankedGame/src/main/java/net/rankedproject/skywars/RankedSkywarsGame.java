package net.rankedproject.skywars;

import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.metadata.GameMetadata;
import net.rankedproject.gameapi.state.GameState;
import net.rankedproject.gameapi.world.GameWorldData;
import net.rankedproject.skywars.state.GameWaitingState;
import net.rankedproject.spigot.CommonPlugin;
import org.jetbrains.annotations.NotNull;

public class RankedSkywarsGame extends Game {

    public RankedSkywarsGame(@NotNull CommonPlugin plugin, @NotNull GameMetadata metadata) {
        super(plugin, metadata);
    }

    @Override
    public GameState getInitState() {
        return new GameWaitingState();
    }

    @Override
    public GameWorldData getWorldData() {
        return GameWorldData.builder()
                .worldName("")
                .build();
    }
}
