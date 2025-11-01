package net.rankedproject.gameapi.metadata;

import org.jetbrains.annotations.NotNull;

public interface GameMetadata {

    @NotNull
    String getWorldName();

    @NotNull
    String getGameDisplayName();

    @NotNull
    String getGameIdentifier();
}