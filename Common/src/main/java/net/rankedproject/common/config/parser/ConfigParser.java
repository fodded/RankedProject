package net.rankedproject.common.config.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Reader;
import java.util.List;

public interface ConfigParser<T> {

    @NotNull
    ParsedConfig<T> parse(@NotNull Reader reader);

    @NotNull
    <U> U get(@NotNull String path, @NotNull ParsedConfig<T> parsedConfig, @Nullable Class<? extends U> returnType);

    @NotNull
    default <U> U get(@NotNull String path, @NotNull ParsedConfig<T> parsedConfig) {
        return get(path, parsedConfig, null);
    }

    @NotNull
    default Integer getAsInt(@NotNull String path, @NotNull ParsedConfig<T> parsedConfig) {
        return get(path, parsedConfig, Integer.class);
    }

    @NotNull
    default String getAsString(@NotNull String path, @NotNull ParsedConfig<T> parsedConfig) {
        return get(path, parsedConfig, String.class);
    }

    @NotNull
    @SuppressWarnings("unchecked")
    default List<String> getAsList(@NotNull String path, @NotNull ParsedConfig<T> parsedConfig) {
        return this.<List<String>>get(path, parsedConfig, (Class<? extends List<String>>) List.class);
    }
}
