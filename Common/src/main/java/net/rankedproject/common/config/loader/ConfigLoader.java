package net.rankedproject.common.config.loader;

import net.rankedproject.common.config.Config;
import org.jetbrains.annotations.NotNull;

public interface ConfigLoader {

    @NotNull
    <T extends Config> T load(@NotNull String name);
}
