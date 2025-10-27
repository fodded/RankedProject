package net.rankedproject.spigot.config;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;
import net.rankedproject.common.config.loader.ConfigLoader;
import net.rankedproject.spigot.CommonPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Singleton
@RequiredArgsConstructor(onConstructor_={@Inject})
public class BukkitConfigLoader implements ConfigLoader {

    private static final String PATH = "configs/%s";
    private final CommonPlugin plugin;

    @NotNull
    @Override
    public Reader load(@NotNull String name) {
        try (var resource = plugin.getClass().getClassLoader().getResourceAsStream(PATH.formatted(name))) {
            Preconditions.checkNotNull(resource, "Requested config file named %s is not found".formatted(name));
            return new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}