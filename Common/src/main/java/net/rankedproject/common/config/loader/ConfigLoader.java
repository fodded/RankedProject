package net.rankedproject.common.config.loader;

import org.jetbrains.annotations.NotNull;

import java.io.Reader;

public interface ConfigLoader {

    /**
     *
     *
     * @param name
     * @return
     */
    @NotNull
    Reader load(@NotNull String name);
}