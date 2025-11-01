package net.rankedproject.skywars;

import net.rankedproject.common.rest.impl.RankedPlayerRestClient;
import net.rankedproject.gameapi.config.MapInfoConfig;
import net.rankedproject.spigot.CommonPlugin;
import net.rankedproject.spigot.server.RankedServer;
import net.rankedproject.spigot.server.RankedServerBuilder;

public class GamePlugin extends CommonPlugin {

    @Override
    public RankedServer rankedServer() {
        return new RankedServerBuilder()
                .setName("Lobby")
                .addConfig(MapInfoConfig.class)
                .addRequiredPlayerData(RankedPlayerRestClient.class)
                .build();
    }
}