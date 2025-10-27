package net.rankedproject.skywars.metadata;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;
import net.rankedproject.common.config.ConfigProvider;
import net.rankedproject.gameapi.metadata.GameMetadataParser;
import net.rankedproject.skywars.config.MapInfoConfig;
import org.jetbrains.annotations.NotNull;

@Singleton
@RequiredArgsConstructor(onConstructor_={@Inject})
public class RankedGameMetadataParser implements GameMetadataParser<RankedGameMetadata> {

    private final Injector injector;

    @NotNull
    @Override
    public RankedGameMetadata parse(@NotNull String gameIdentifier) {
        return new RankedGameMetadata(null, null, null);
    }
}
