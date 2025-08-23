package net.rankedproject.spigot.loader;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

public class InstantiatorRegistry {

    private final Map<Class<? extends Instantiator<?>>, Instantiator<?>> loaders = new IdentityHashMap<>();

    public <U, T extends Instantiator<U>> void register(Class<? extends T> loaderType, T loader) {
        loaders.put(loaderType, loader);
    }

    @Nullable
    public <U, T extends Instantiator<U>> T get(Class<? extends T> loaderType) {
        return loaderType.cast(loaders.get(loaderType));
    }

    @UnmodifiableView
    public Collection<Instantiator<?>> getAll() {
        return Collections.unmodifiableCollection(loaders.values());
    }
}
