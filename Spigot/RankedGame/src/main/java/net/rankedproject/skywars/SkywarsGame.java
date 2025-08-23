package net.rankedproject.skywars;

import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.state.GameState;
import net.rankedproject.gameapi.world.GameWorldData;
import net.rankedproject.spigot.CommonPlugin;

public class SkywarsGame extends Game {

    public SkywarsGame(CommonPlugin plugin) {
        super(plugin);
    }

    @Override
    public GameState getInitState() {
        return null;
    }

    @Override
    public GameWorldData getWorldData() {
        return null;
    }
}
