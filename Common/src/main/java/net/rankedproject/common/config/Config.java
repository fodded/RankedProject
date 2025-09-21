package net.rankedproject.common.config;

import org.jetbrains.annotations.NotNull;

public interface Config {

    @NotNull
    String name();

    int version();
}

/**
 * ConfigProvider.get(SkywarsConfig.class)
 *               .path("server.maps.skywars.random.players.max")
 *               .placeholder("player-name", player.getName())
 *               .get(Int.class);
 */