package net.rankedproject.common.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.rankedproject.common.rest.request.RequestFactory;
import net.rankedproject.common.rest.request.type.RequestContent;
import net.rankedproject.common.rest.request.type.RequestType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Abstract class representing a generic REST client.
 * Provides basic HTTP operations with retry mechanisms.
 *
 * @param <V> the type of the value being sent and received
 */
public abstract class RestClient<V> implements IRestClient<V> {

    private static final int MAX_RETRY_ATTEMPTS = 5;

    protected static final Logger LOGGER = Logger.getLogger(RestClient.class.getName());
    protected static final Gson GSON = new GsonBuilder().serializeNulls().create();

    protected static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

    /**
     * Executes a GET request and returns the response as a JsonElement.
     *
     * @param request the HTTP request to execute
     * @return the parsed JSON response, or null if unsuccessful
     */
    public JsonElement retrieve(Request request) {
        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                LOGGER.warning("GET request failed: " + response.code());
                return null;
            }
            return GSON.fromJson(response.body().string(), JsonElement.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve resource", e);
        }
    }

    /**
     * Executes a GET request and returns the response as a JsonElement.
     *
     * @return the parsed JSON response, or null if unsuccessful
     */
    public JsonElement retrieve() {
        Request request = RequestFactory.getInstance().get(RequestType.GET);
        return retrieve(request);
    }

    /**
     * Executes a GET request and returns the response as a JsonElement.
     *
     * @param requestContent DTO class containing information to modify the output request.
     * @return the parsed JSON response, or null if unsuccessful
     */
    public JsonElement retrieve(RequestContent requestContent) {
        Request request = RequestFactory.getInstance().get(RequestType.GET, requestContent);
        return retrieve(request);
    }

    /**
     * Send an HTTP Request.
     *
     * @param request the HTTP request to execute
     */
    public void sendRequest(Request request) {
        executeWithRetry(request, 1);
    }

    /**
     * Send an HTTP Request.
     *
     * @param requestType the HTTP request type to execute
     */
    public void sendRequest(RequestType requestType) {
        Request request = RequestFactory.getInstance().get(requestType);
        executeWithRetry(request, 1);
    }

    /**
     * Send an HTTP Request.
     *
     * @param requestType the HTTP request type to execute
     * @param requestContent DTO class containing information to modify the output request.
     */
    public void sendRequest(RequestType requestType, RequestContent requestContent) {
        Request request = RequestFactory.getInstance().get(requestType, requestContent);
        executeWithRetry(request, 1);
    }

    /**
     * Executes a request with retry logic.
     *
     * @param request        the HTTP request to execute
     * @param attemptedTimes attempts taken to send a request
     */
    private void executeWithRetry(Request request, int attemptedTimes) {
        if (attemptedTimes >= MAX_RETRY_ATTEMPTS) {
            LOGGER.warning("Attempt #" + attemptedTimes + " failed sending request: " + request.toString());
            return;
        }

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                executeWithRetry(request, attemptedTimes + 1);
            }
        } catch (IOException e) {
            throw new RuntimeException("Request execution failed", e);
        }
    }
}