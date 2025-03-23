package net.rankedproject.common.rest.request;

import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Factory class for creating and managing HTTP requests using OkHttp.
 * Supports dynamic request composition using consumers for URL and request building.
 */
public class RequestFactory {

    @Getter
    private static final RequestFactory instance = new RequestFactory();

    protected static final String BASE_URL = Optional
            .ofNullable(System.getenv("REST_API_URL"))
            .orElse("http://localhost:8080/");

    private final Map<RequestType, BiFunction<Consumer<HttpUrl.Builder>, Consumer<Request.Builder>, Request>> requests
            = new EnumMap<>(RequestType.class);

    private RequestFactory() {
        registerDefaults();
    }

    /**
     * Registers a request function for a specific request type.
     *
     * @param type     The type of request to register.
     * @param function The function that builds the request.
     */
    public void register(
            RequestType type,
            BiFunction<Consumer<HttpUrl.Builder>, Consumer<okhttp3.Request.Builder>, Request> function
    ) {
        this.requests.put(type, function);
    }

    /**
     * Retrieves a request of the given type.
     *
     * @param type The type of request to retrieve.
     * @return The constructed request.
     */
    public Request get(RequestType type) {
        return requests.get(type).apply(null, null);
    }

    /**
     * Retrieves a request with optional consumers for modifying the HTTP URL and request builder.
     *
     * @param type           The request type.
     * @param httpBuilder    Consumer to modify the HTTP URL.
     * @param requestBuilder Consumer to modify the request builder.
     * @return The constructed request.
     */
    public Request get(RequestType type, Consumer<HttpUrl.Builder> httpBuilder, Consumer<Request.Builder> requestBuilder) {
        return requests.get(type).apply(httpBuilder, requestBuilder);
    }

    /**
     * Retrieves a request with an HTTP URL builder.
     *
     * @param type        The request type.
     * @param httpBuilder Consumer to modify the HTTP URL.
     * @return The constructed request.
     */
    public Request getWithHttpBuilder(RequestType type, Consumer<HttpUrl.Builder> httpBuilder) {
        return requests.get(type).apply(httpBuilder, null);
    }

    /**
     * Retrieves a request with a request builder.
     *
     * @param type           The request type.
     * @param requestBuilder Consumer to modify the request builder.
     * @return The constructed request.
     */
    public Request getWithRequestBuilder(RequestType type, Consumer<Request.Builder> requestBuilder) {
        return requests.get(type).apply(null, requestBuilder);
    }

    private void registerDefaults() {
        register(RequestType.GET, (httpBuilder, requestBuilder) -> {
            HttpUrl.Builder urlBuilder = HttpUrl.get(BASE_URL).newBuilder();
            return composeFlexibleRequestBuilder(httpBuilder, requestBuilder, urlBuilder).build();
        });
        register(RequestType.POST, (httpBuilder, requestBuilder) -> {
            HttpUrl.Builder urlBuilder = HttpUrl.get(BASE_URL).newBuilder();
            return composeFlexibleRequestBuilder(httpBuilder, requestBuilder, urlBuilder).build();
        });
        register(RequestType.PUT, (httpBuilder, requestBuilder) -> {
            HttpUrl.Builder urlBuilder = HttpUrl.get(BASE_URL).newBuilder();
            return composeFlexibleRequestBuilder(httpBuilder, requestBuilder, urlBuilder).build();
        });
        register(RequestType.DELETE, (httpBuilder, requestBuilder) -> {
            HttpUrl.Builder urlBuilder = HttpUrl.get(BASE_URL).newBuilder();
            return composeFlexibleRequestBuilder(httpBuilder, requestBuilder, urlBuilder).build();
        });
    }

    /**
     * Composes a flexible request builder by applying optional consumers for modifying the URL and request.
     *
     * @param httpBuilder    Consumer to modify the HTTP URL.
     * @param requestBuilder Consumer to modify the request.
     * @param urlBuilder     The URL builder to be modified.
     * @return A configured Request.Builder instance.
     */
    private Request.Builder composeFlexibleRequestBuilder(
            @Nullable Consumer<HttpUrl.Builder> httpBuilder,
            @Nullable Consumer<Request.Builder> requestBuilder,
            @NotNull HttpUrl.Builder urlBuilder
    ) {
        if (httpBuilder != null) httpBuilder.accept(urlBuilder);

        Request.Builder nativeBuilder = new Request.Builder()
                .url(urlBuilder.build())
                .addHeader("Content-Type", "application/json");

        if (requestBuilder != null) requestBuilder.accept(nativeBuilder);
        return nativeBuilder;
    }
}
