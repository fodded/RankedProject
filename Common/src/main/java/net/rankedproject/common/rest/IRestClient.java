package net.rankedproject.common.rest;

import com.google.gson.JsonElement;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public interface IRestClient<V> {

    ExecutorService EXECUTOR_SERVICE = Executors.newVirtualThreadPerTaskExecutor();

    /**
     * @return REST Repository URL
     */
    String getRepository();

    /**
     * @return Expected DTO Class
     */
    Class<V> getReturnType();

    JsonElement get(Request request);
    void put(Request request);
    void post(Request request);
    void delete(Request request);

    default CompletableFuture<JsonElement> getAsync(Request request) {
        return async(() -> get(request));
    }

    default CompletableFuture<Void> putSave(Request request) {
        return async(() -> put(request));
    }

    default CompletableFuture<Void> postAsync(Request request) {
        return async(() -> post(request));
    }

    default CompletableFuture<Void> deleteAsync(Request request) {
        return async(() -> delete(request));
    }

    default CompletableFuture<Void> async(Runnable action) {
        return CompletableFuture.runAsync(action, EXECUTOR_SERVICE);
    }

    default <T> CompletableFuture<T> async(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, EXECUTOR_SERVICE);
    }

    default void shutdown() {
        EXECUTOR_SERVICE.shutdown();
    }
}
