package net.rankedproject.spigot.world;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Function;

public interface Spawn {

    /**
     * Specifies the way to load a spawn's world
     *
     * @return A WorldLoaderType
     */
    @NotNull
    String getWorldName();

    /**
     * Defines the behavior on player join to teleport a player to spawn's location.
     * Could be static lobby location or dynamically changing last player's position.
     *
     * @return Behavior to teleport player to a location
     */
    @NotNull
    Function<Player, Location> getLocationFinder();

    /**
     * A list of flags relative to the spawn's world.
     * The flags determine whether an action is allowed or not in the respective world.
     *
     * @return A Set of flags relevant to the spawn's world
     */
    @NotNull
    Set<SpawnFlag> getFlags();
}
