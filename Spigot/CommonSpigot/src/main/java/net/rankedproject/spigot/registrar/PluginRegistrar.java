package net.rankedproject.spigot.registrar;

import java.util.concurrent.CompletableFuture;

public interface PluginRegistrar {

    /**
     * Registers necessities on plugin startup
     */
    CompletableFuture<?> register();
}
