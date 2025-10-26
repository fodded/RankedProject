package net.rankedproject.common.config.reader;

import net.rankedproject.common.config.parser.ParsedConfig;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface ConfigReader<T> {

    @NotNull
    Object get(@NotNull ParsedConfig<T> parsedConfig, @NotNull ConfigReadOption readOption);

    @NotNull
    String getAsString(@NotNull ParsedConfig<T> parsedConfig, @NotNull ConfigReadOption readOption);

    @NotNull
    Integer getAsInt(@NotNull ParsedConfig<T> parsedConfig, @NotNull ConfigReadOption readOption);

    @NotNull
    CompletableFuture<Void> sendMessage(@NotNull ParsedConfig<T> parsedConfig, @NotNull ConfigReadOption readOption);

    @NotNull
    Class<T> getParseType();
}
