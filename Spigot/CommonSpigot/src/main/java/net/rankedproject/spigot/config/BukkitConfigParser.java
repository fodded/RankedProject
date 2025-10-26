package net.rankedproject.spigot.config;

import com.google.inject.Singleton;
import net.rankedproject.common.config.parser.ConfigParser;
import net.rankedproject.common.config.parser.ParsedConfig;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.Reader;

@Singleton
public class BukkitConfigParser implements ConfigParser<YamlConfiguration> {

    // codecs map here

    @NotNull
    @Override
    public <U> U get(@NotNull String path, @NotNull ParsedConfig<YamlConfiguration> parsedConfig) {
        var data = parsedConfig.data();
        var dataByPath = data.get(path);

        switch (dataByPath) {
            case Integer id -> {
                // if it was parsed as an integer then pass it to integer codec
            }
            case null -> {}
            default -> throw new IllegalStateException("Unexpected value: " + dataByPath);
        }
        return null;
    }

    @NotNull
    @Override
    public ParsedConfig<YamlConfiguration> parse(@NotNull Reader reader) {
        return new ParsedConfig<>(YamlConfiguration.loadConfiguration(reader), YamlConfiguration.class);
    }
}