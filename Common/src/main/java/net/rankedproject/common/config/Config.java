package net.rankedproject.common.config;

import org.jetbrains.annotations.NotNull;

public interface Config {

    @NotNull
    ConfigMetadata getMetadata();
}