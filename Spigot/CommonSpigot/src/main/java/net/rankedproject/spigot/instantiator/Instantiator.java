package net.rankedproject.spigot.instantiator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Instantiator<T> {

    /**
     * Loads T into memory.
     * <p>
     * This method is typically invoked during server startup or other initialization phases
     * to perform heavy I/O or setup tasks. It should return a fully initialized instance
     * of {@code T}.
     *
     * @return a non-null, fully initialized {@code T} instance ready for use
     */
    @NotNull
    T init();

    /**
     * Retrieves the currently loaded T instance, if available.
     * <p>
     * If {@link #init()} has not been called yet (or failed), this method
     * may return {@code null}.
     *
     * @return the loaded {@code T} instance if available, or {@code null} if not yet loaded
     */
    @Nullable
    T get();
}
