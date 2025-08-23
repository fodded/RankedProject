package net.rankedproject.gameapi.world;

import lombok.Builder;
import lombok.Getter;
import net.rankedproject.gameapi.world.loader.WorldLoaderType;

@Getter
@Builder
public class GameWorldData {

    private final String worldName;

    @Builder.Default
    private final WorldLoaderType worldLoader = WorldLoaderType.SLIME_WORLD;
}
