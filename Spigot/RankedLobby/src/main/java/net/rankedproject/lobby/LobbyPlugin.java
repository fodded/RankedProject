package net.rankedproject.lobby;

import net.rankedproject.common.rest.impl.RankedPlayerRestClient;
import net.rankedproject.spigot.CommonPlugin;
import net.rankedproject.spigot.server.RankedServer;
import net.rankedproject.spigot.server.RankedServerBuilder;

public class LobbyPlugin extends CommonPlugin {

    @Override
    public RankedServer rankedServer() {
        return new RankedServerBuilder()
                .setName("Lobby")
                .addRequiredPlayerData(RankedPlayerRestClient.class)
                .build();
    }
}
