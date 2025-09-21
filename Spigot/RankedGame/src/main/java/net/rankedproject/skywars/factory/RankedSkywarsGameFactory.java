package net.rankedproject.skywars.factory;

import net.rankedproject.game.factory.GameFactory;
import net.rankedproject.skywars.RankedSkywarsGame;
import net.rankedproject.skywars.metadata.RankedGameMetadata;
import net.rankedproject.skywars.metadata.RankedGameMetadataParser;
import net.rankedproject.spigot.CommonPlugin;
import org.jetbrains.annotations.NotNull;

public class RankedSkywarsGameFactory implements GameFactory<RankedSkywarsGame, RankedGameMetadata> {

    @NotNull
    @Override
    public RankedGameMetadataParser getParser() {
        return new RankedGameMetadataParser();
    }

    @NotNull
    @Override
    public RankedSkywarsGame createInstance(@NotNull CommonPlugin plugin, @NotNull RankedGameMetadata metadata) {
        return new RankedSkywarsGame(plugin, metadata);
    }
}
