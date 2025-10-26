package net.rankedproject.common.config.codec.impl;

import net.rankedproject.common.config.codec.ConfigCodec;
import org.jetbrains.annotations.NotNull;

public class StringConfigCodec implements ConfigCodec<String> {

    @NotNull
    @Override
    public String parse(@NotNull String message) {
        return "";
    }

    @NotNull
    @Override
    public Class<?> getReturnType() {
        return null;
    }
}
