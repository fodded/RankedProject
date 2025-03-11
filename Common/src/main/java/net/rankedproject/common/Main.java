package net.rankedproject.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.rankedproject.common.rest.impl.RankedPlayerRestAccessor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final String REST_API_IP_ADDRESS = Optional.ofNullable(System.getenv("REST_API_IP_ADDRESS")).orElse("http://localhost:8080/");

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newVirtualThreadPerTaskExecutor();

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .executor(EXECUTOR_SERVICE)
            .build();

    public static void main(String[] args) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        RankedPlayerRestAccessor rankedPlayerRestAccessor = new RankedPlayerRestAccessor();
        rankedPlayerRestAccessor
                .getAsync(UUID.fromString("c2d3d415-dbdd-4d1a-92a5-33badd11d4be"))
                .thenAccept(rankedPlayer -> {
                    System.out.println(rankedPlayer.getId() + " was retrieved " + rankedPlayer.toString());
                    rankedPlayer.setDeaths(1000);
                    rankedPlayerRestAccessor.update(rankedPlayer);
                });

        /*
        getAsync("ranked_players/getOrCreate/c2d3d415-dbdd-4d1a-92a5-33badd11d4be", RankedPlayer.class)
                .thenAccept(rankedPlayer -> {
                    System.out.println(rankedPlayer.getLastSeenName());
                    System.out.println(rankedPlayer.getIpAddress());
                    System.out.println(rankedPlayer.getId());
                });*/
    }

    public static <T> CompletableFuture<T> getAsync(String request, Class<T> classType) {
        return HTTP_CLIENT.sendAsync(HttpRequest.newBuilder()
                        .uri(URI.create(REST_API_IP_ADDRESS + request))
                        .timeout(Duration.ofSeconds(5))
                        .GET()
                        .build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(result -> {
                    if (result.statusCode() != 200) return null;

                    String jsonResult = result.body();
                    return GSON.fromJson(jsonResult, classType);
                })
                .exceptionally(throwable -> null);
    }

    public static <T> CompletableFuture<List<T>> getAsyncList(String request, Class<T> classType) {
        return HTTP_CLIENT.sendAsync(HttpRequest.newBuilder()
                        .uri(URI.create(REST_API_IP_ADDRESS + request))
                        .timeout(Duration.ofSeconds(5))
                        .GET()
                        .build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(result -> {
                    if (result.statusCode() != 200) return null;

                    String jsonResult = result.body();
                    JsonElement jsonElement = GSON.fromJson(jsonResult, JsonElement.class);

                    return jsonElement.getAsJsonArray()
                            .asList()
                            .stream()
                            .map(element -> GSON.fromJson(element, classType))
                            .toList();
                })
                .exceptionally(throwable -> null);
    }
}
