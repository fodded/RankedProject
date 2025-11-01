package net.rankedproject.skywars.metadata;

import net.rankedproject.gameapi.metadata.GameMetadata;
import org.jetbrains.annotations.NotNull;

public record RankedGameMetadata(String worldName, String displayName, String identifier) implements GameMetadata {

    @NotNull
    @Override
    public String getWorldName() {
        return worldName;
    }

    @NotNull
    @Override
    public String getGameDisplayName() {
        return displayName;
    }

    @NotNull
    @Override
    public String getGameIdentifier() {
        return identifier;
    }
}