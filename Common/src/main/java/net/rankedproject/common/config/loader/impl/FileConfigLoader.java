package net.rankedproject.common.config.loader.impl;

import com.google.inject.Singleton;
import net.rankedproject.common.config.loader.ConfigLoader;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;

@Singleton
public class FileConfigLoader implements ConfigLoader {

    @Override
    public @NotNull BufferedReader load(@NotNull String name) {
        return null;
    }
}
