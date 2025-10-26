package net.rankedproject.skywars.metadata;

import net.rankedproject.gameapi.metadata.GameMetadata;

public record RankedGameMetadata(String worldName, String displayName, String identifier) implements GameMetadata {

    @Override
    public String getGameDisplayName() {
        return displayName;
    }

    @Override
    public String getGameIdentifier() {
        return identifier;
    }
}