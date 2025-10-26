package net.rankedproject.common.config;

import net.rankedproject.common.config.loader.ConfigLoader;
import net.rankedproject.common.config.parser.ConfigParser;
import org.jetbrains.annotations.NotNull;

public interface Config {

    @NotNull
    String getName();

    int getVersion();

    ConfigParser<?> getParser();

    ConfigLoader getConfigLoader();
}

/**
 * ConfigProvider.get(SkywarsConfig.class)
 *               .path("server.maps.skywars.random.players.max")
 *               .placeholder("player-name", player.getName())
 *               .get(Int.class);
 */

/**
 * skywarsConfig.as(Int.class)
 *      .path("server.maps.skywars")
 *      .placeholder("")
 *      .placeholder("") -> ConfigReader
 *      .get();
 *
 */