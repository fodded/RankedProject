package net.rankedproject.spigot.server;

import net.rankedproject.common.rest.type.PlayerRestClient;
import net.rankedproject.spigot.registrar.PluginRegistrar;

import java.util.Collection;

public record RankedServer(
        Collection<PluginRegistrar> registrars,
        Collection<Class<? extends PlayerRestClient<?>>> requiredPlayerData,
        String name
) {
}
