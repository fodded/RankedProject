package net.rankedproject.common.rest.request.type;

import lombok.Builder;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

@Builder
public record RequestContent(@Nullable Consumer<HttpUrl.Builder> httpBuilder,
                             @Nullable Consumer<Request.Builder> requestBuilder) {
}
