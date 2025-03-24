package net.rankedproject.common.registry;

import lombok.Getter;
import net.rankedproject.common.rest.provider.RestClientRegistry;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

public class RegistryProvider {

    @Getter
    private static final RegistryProvider instance = new RegistryProvider();

    private final Map<Class<?>, BaseRegistry<?, ?>> registries = Collections.synchronizedMap(new IdentityHashMap<>());

    public RegistryProvider() {
        registerDefaults();
    }

    public void registerDefaults() {
        register(new RestClientRegistry());
    }

    public void register(BaseRegistry<?, ?> registry) {
        registries.put(registry.getClass(), registry);
    }

    @SuppressWarnings("unchecked")
    public <K, V, T extends BaseRegistry<K, V>> T getRegistry(Class<?> classType) {
        return (T) registries.get(classType);
    }

    public Map<Class<?>, BaseRegistry<?, ?>> getRegistries() {
        return Map.copyOf(registries);
    }

    public static <T, U> BaseRegistry<T, U> get(Class<?> classType) {
        return getInstance().getRegistry(classType);
    }
}
