package net.rankedproject.common.rest;

import com.google.gson.JsonElement;
import net.rankedproject.common.rest.request.type.RequestContent;
import okhttp3.MediaType;
import okhttp3.Request;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public interface IRestClient<V> {

    MediaType JSON = MediaType.get("application/json");
    ExecutorService EXECUTOR_SERVICE = Executors.newVirtualThreadPerTaskExecutor();

    /**
     * @return REST Repository URL
     */
    String getRepository();

    /**
     * @return Expected DTO Class
     */
    Class<V> getReturnType();

    JsonElement getAsJson(Request request);
    void put(RequestContent request);
    void post(RequestContent request);
    void delete(RequestContent request);

    default CompletableFuture<JsonElement> getAsJsonAsync(Request request) {
        return async(() -> getAsJson(request));
    }

    default CompletableFuture<Void> putSave(RequestContent requestContent) {
        return async(() -> put(requestContent));
    }

    default CompletableFuture<Void> postAsync(RequestContent requestContent) {
        return async(() -> post(requestContent));
    }

    default CompletableFuture<Void> deleteAsync(RequestContent requestContent) {
        return async(() -> delete(requestContent));
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
