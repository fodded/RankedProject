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
    public static <V, T extends RestClient<? extends V>> T get(Class<? extends T> classType) {
        return classType.cast(
                RegistryProvider.getInstance()
                        .getRegistry(RestClientRegistry.class)
                        .get(classType)
        );
    }

    /**
     * Retrieves an instance of the specified {@code RestClient} type from the registry based on the return type.
     *
     * <p>This method looks up a registered {@code RestClient} whose return type matches the given {@code classType}.
     * If found, it returns an instance of the specified {@code RestClient}; otherwise, it returns {@code null}.
     *
     * @param <V>       The value type handled by the {@code RestClient}, typically a subtype of {@code BasePlayer}.
     * @param <T>       The specific implementation of {@code RestClient} to be retrieved.
     * @param classType The class type of the value associated with the {@code RestClient}.
     * @return An instance of the matching {@code RestClient} if found, otherwise {@code null}.
     * @throws ClassCastException if the retrieved instance cannot be cast to the specified {@code RestClient} type.
     * @implNote The method performs an unchecked cast to {@code T}, which is suppressed via {@code @SuppressWarnings("unchecked")}.
     */
    @SuppressWarnings("unchecked")
    public static <V, T extends RestClient<V>> T getByReturnType(Class<? extends V> classType) {
        RestClientRegistry registry = RegistryProvider.getInstance().getRegistry(RestClientRegistry.class);
        RestClient<?> restClient = registry.getAllRegistered().values()
                .stream()
                .filter(client -> client.getReturnType() == classType)
                .findFirst()
                .orElse(null);

        return restClient == null ? null : (T) restClient;
    }
}
