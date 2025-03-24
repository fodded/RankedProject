package net.rankedproject.common.rest.type;

import com.google.gson.JsonElement;
import net.rankedproject.common.rest.RestClient;
import net.rankedproject.common.rest.request.type.RequestContent;
import net.rankedproject.common.rest.request.type.RequestType;
import okhttp3.RequestBody;

import java.util.Collection;

public abstract class CrudRestClient<V> extends RestClient<V> {

    public Collection<V> getAll() {
        JsonElement jsonElement = getAsJson(RequestContent.builder()
                .httpBuilder(builder -> builder.addPathSegments(getRepository()))
                .build());

        return jsonElement.getAsJsonArray().asList()
                .stream()
                .map(element -> GSON.fromJson(element, getReturnType()))
                .toList();
    }

    public V get(String... params) {
        JsonElement jsonElement = getAsJson(RequestContent.builder()
                .httpBuilder(builder -> {
                    for (String param : params) {
                        builder.addPathSegment(param);
                    }
                })
                .build());

        return GSON.fromJson(jsonElement, getReturnType());
    }

    public V get(RequestContent content) {
        JsonElement jsonElement = getAsJson(content);
        return GSON.fromJson(jsonElement, getReturnType());
    }

    public void put(V value) {
        sendRequest(RequestType.PUT, RequestContent.builder()
                .requestBuilder(builder -> builder.put(RequestBody.create(GSON.toJson(value), JSON)))
                .build());
    }

    public void put(RequestContent content) {
        sendRequest(RequestType.PUT, content);
    }

    public void post(V value) {
        sendRequest(RequestType.POST, RequestContent.builder()
                .requestBuilder(builder -> builder.post(RequestBody.create(GSON.toJson(value), JSON)))
                .build());
    }

    public void post(RequestContent content) {
        sendRequest(RequestType.POST, content);
    }

    public void delete(V value) {
        sendRequest(RequestType.DELETE, RequestContent.builder()
                .requestBuilder(builder -> builder.delete(RequestBody.create(GSON.toJson(value), JSON)))
                .build());
    }

    public void delete(RequestContent content) {
        sendRequest(RequestType.DELETE, content);
    }
}
