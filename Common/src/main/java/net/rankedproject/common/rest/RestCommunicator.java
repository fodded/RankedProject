package net.rankedproject.common.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class RestCommunicator<K, V> {

    private static final int MAX_RETRY_ATTEMPTS = 5;

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();
    private static final Gson GSON = new GsonBuilder().create();

    protected static final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    protected static final MediaType JSON = MediaType.get("application/json");

    protected static final String BASE_URL = Optional
            .ofNullable(System.getenv("REST_API_URL"))
            .orElse("http://localhost:8080/");

    public V get(K key) {
        Request request = new Request.Builder()
                .url(HttpUrl.get(BASE_URL)
                        .newBuilder()
                        .addPathSegment(getRepository())
                        .addPathSegment(key.toString())
                        .build())
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) return null;

            ResponseBody body = response.body();
            if (body == null) return null;

            String parsedJson = body.string();
            return GSON.fromJson(parsedJson, getReturnType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(V value) {
        update(value, 1);
    }

    public void save(V value) {
        save(value, 1);
    }

    public CompletableFuture<V> getAsync(K key) {
        return CompletableFuture.supplyAsync(() -> get(key), EXECUTOR_SERVICE);
    }

    public CompletableFuture<Void> updateAsync(V value) {
        return CompletableFuture.runAsync(() -> update(value), EXECUTOR_SERVICE);
    }

    public CompletableFuture<Void> updateSave(V value) {
        return CompletableFuture.runAsync(() -> save(value), EXECUTOR_SERVICE);
    }

    private void update(V value, int attemptedTimes) {
        Request request = new Request.Builder()
                .url(HttpUrl.get(BASE_URL)
                        .newBuilder()
                        .addPathSegments(getRepository())
                        .build())
                .put(RequestBody.create(GSON.toJson(value), JSON))
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) return;
            if (attemptedTimes >= MAX_RETRY_ATTEMPTS) return;

            update(value, attemptedTimes + 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void save(V value, int attemptedTimes) {
        Request request = new Request.Builder()
                .url(HttpUrl.get(BASE_URL)
                        .newBuilder()
                        .addPathSegments(getRepository())
                        .build())
                .post(RequestBody.create(GSON.toJson(value), JSON))
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) return;
            if (attemptedTimes >= MAX_RETRY_ATTEMPTS) return;

            save(value, attemptedTimes + 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract String getRepository();
    protected abstract Class<V> getReturnType();
}
