package net.rankedproject.common.rest.provider;

import net.rankedproject.common.registry.RegistryProvider;
import net.rankedproject.common.rest.RestClient;

public class RestProvider {

    /**
     * Retrieves an instance of the specified {@code RestClient} type from the registry.
     *
     * @param <V>       The value type of the {@code RestClient}.
     * @param <T>       The specific {@code RestClient} implementation.
     * @param classType The {@code RestClient} class type to retrieve.
     * @return An instance of the specified {@code RestClient} type, or {@code null} if not found.
     * @throws ClassCastException if the retrieved instance cannot be cast to the specified type.
     */
    public static <V, T extends RestClient<V>> T get(Class<T> classType) {
        return classType.cast(
                RegistryProvider.getInstance()
                        .getRegistry(RestCommunicatorRegistry.class)
                        .get(classType)
        );
    }
}
