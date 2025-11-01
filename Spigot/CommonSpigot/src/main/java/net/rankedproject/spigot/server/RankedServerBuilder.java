package net.rankedproject.spigot.server;

import net.rankedproject.common.config.Config;
import net.rankedproject.common.rest.type.PlayerRestClient;
import net.rankedproject.spigot.instantiator.Instantiator;
import net.rankedproject.spigot.registrar.PluginRegistrar;
import net.rankedproject.spigot.world.Spawn;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RankedServerBuilder {

    private final List<Instantiator<?>> loaders = new ArrayList<>();
    private final List<PluginRegistrar> registrars = new ArrayList<>();

    private final List<Class<? extends PlayerRestClient<?>>> requiredPlayerData = new ArrayList<>();
    private final List<Class<? extends Config>> configs = new ArrayList<>();

    private Spawn spawn;
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
    public RankedServerBuilder addInstantiator(@NotNull Instantiator<?> instantiator) {
        this.loaders.add(instantiator);
        return this;
    }

    @NotNull
    public RankedServerBuilder addInstantiator(@NotNull Collection<Instantiator<?>> instantiator) {
        this.loaders.addAll(instantiator);
        return this;
    }

    @NotNull
    public RankedServerBuilder addConfig(@NotNull Class<? extends Config> config) {
        this.configs.add(config);
        return this;
    }

    @NotNull
    public RankedServerBuilder addSpawn(@NotNull Spawn spawn) {
        this.spawn = spawn;
        return this;
    }

    @NotNull
    public RankedServer build() {
        return new RankedServer(loaders, registrars, requiredPlayerData, configs, spawn, name);
    }
}