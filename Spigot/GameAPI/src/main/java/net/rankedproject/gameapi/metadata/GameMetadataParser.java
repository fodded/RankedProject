package net.rankedproject.gameapi.metadata;

import org.jetbrains.annotations.NotNull;

public interface GameMetadataParser<T extends GameMetadata> {

    /**
     * Parses GameMetadata object from a config by provided game identifier
     *
     * @param gameIdentifier Unique game's metadata identifier
     * @return Parsed game metadata
     */
    @NotNull
    T parse(@NotNull String gameIdentifier);
}
