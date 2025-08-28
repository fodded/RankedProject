package net.rankedproject.skywars;

import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.state.GameState;
import net.rankedproject.gameapi.world.GameWorldData;

public class RankedSkywarsGame extends Game {

    private final String worldName;

    @Override
    public GameState getInitState() {
        return null;
    }

    @Override
    public GameWorldData getWorldData() {
        return GameWorldData.builder()
                .worldName("")
                .build();
    }
}
