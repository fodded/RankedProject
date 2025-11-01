package net.rankedproject.spigot.world.loader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorldLoaderType {

    SLIME_WORLD(new SlimeWorldLoader());

    private final WorldLoader loader;
}
