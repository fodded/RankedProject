package net.rankedproject.common.util;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class CacheWrapper<T> {

    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    // Some weak key based cache which is going to remove the entry after 5 minutes if the object hasn't been used for all that time
    private static final Map<Object, CacheWrapper<?>> cachedObjectsMap = new HashMap<>();

    private final WeakReference<T> storedObject;

    public CacheWrapper(T object, Consumer<T> cacheAction) {
        this.storedObject = new WeakReference<>(object);

        cacheAction.accept(object);
        SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(() -> cacheAction.accept(object), 0, 1, TimeUnit.SECONDS);
    }

    public T getStoredObject() {
        return storedObject.get();
    }

    @SuppressWarnings("unchecked")
    public static <T> CacheWrapper<T> of(T object, Consumer<T> cacheAction) {
        return (CacheWrapper<T>) cachedObjectsMap.computeIfAbsent(object, wrapper -> new CacheWrapper<>(object, cacheAction));
    }
}