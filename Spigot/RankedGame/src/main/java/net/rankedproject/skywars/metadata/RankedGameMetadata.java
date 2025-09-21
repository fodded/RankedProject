package net.rankedproject.skywars.metadata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.rankedproject.gameapi.metadata.GameMetadata;

@Getter
@RequiredArgsConstructor
public class RankedGameMetadata implements GameMetadata {

    private final String worldName, displayName, identifier;

    @Override
    public String getWorldName() {
        return worldName;
    }

    @Override
    public String getGameDisplayName() {
        return displayName;
    }

    @Override
    public String getGameIdentifier() {
        return identifier;
    }
}
