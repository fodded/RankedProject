package net.rankedproject.spigot.server;

import net.rankedproject.common.rest.type.PlayerRestClient;
import net.rankedproject.spigot.registrar.PluginRegistrar;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class RankedServerBuilder {

    private final Collection<PluginRegistrar> registrars = new ArrayList<>();
    private final Collection<Class<? extends PlayerRestClient<?>>> requiredPlayerData = new ArrayList<>();

    private String name;

    @NotNull
    public RankedServerBuilder setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    @NotNull
    public RankedServerBuilder addRegistrar(@NotNull PluginRegistrar registrar) {
        this.registrars.add(registrar);
        return this;
    }

    @NotNull
    public RankedServerBuilder addRegistrar(@NotNull Collection<PluginRegistrar> registrars) {
        this.registrars.addAll(registrars);
        return this;
    }

    @NotNull
    public RankedServerBuilder addRequiredPlayerData(@NotNull Class<? extends PlayerRestClient<?>> requiredPlayerDataClass) {
        this.requiredPlayerData.add(requiredPlayerDataClass);
        return this;
    }

    @NotNull
    public RankedServerBuilder addRequiredPlayerData(@NotNull Collection<Class<? extends PlayerRestClient<?>>> requiredPlayerDataClasses) {
        this.requiredPlayerData.addAll(requiredPlayerDataClasses);
        return this;
    }

    @NotNull
    public RankedServer build() {
        return new RankedServer(registrars, requiredPlayerData, name);
    }
}