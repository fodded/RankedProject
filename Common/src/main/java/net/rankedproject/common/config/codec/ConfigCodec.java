package net.rankedproject.common.config.codec;

import org.jetbrains.annotations.NotNull;

public interface ConfigCodec<T> {

    @NotNull
    T parse(@NotNull String message);

    @NotNull
    Class<?> getReturnType();
}
