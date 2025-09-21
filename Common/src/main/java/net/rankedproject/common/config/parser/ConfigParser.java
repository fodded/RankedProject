package net.rankedproject.common.config.parser;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;

public interface ConfigParser<T> {

    @NotNull
    T parse(@NotNull BufferedReader reader);
}
