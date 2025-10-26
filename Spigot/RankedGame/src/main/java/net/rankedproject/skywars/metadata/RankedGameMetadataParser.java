package net.rankedproject.skywars.metadata;

import net.rankedproject.gameapi.metadata.GameMetadataParser;
import org.jetbrains.annotations.NotNull;

public class RankedGameMetadataParser implements GameMetadataParser<RankedGameMetadata> {

    @NotNull
    @Override
    public RankedGameMetadata parse(@NotNull String gameIdentifier) {
        // TODO: load from config
        return new RankedGameMetadata(null, null, null);
    }
}
