package net.rankedproject.common.rest.provider;

import com.google.inject.Inject;
import net.rankedproject.common.registry.BaseRegistry;
import net.rankedproject.common.rest.RestClient;
import net.rankedproject.common.rest.impl.RankedPlayerRestClient;
import net.rankedproject.common.rest.request.RequestFactory;

import java.util.IdentityHashMap;

public class RestClientRegistry extends BaseRegistry<Class<? extends RestClient<?>>, RestClient<?>> {

    private final RequestFactory requestFactory;

    @Inject
    public RestClientRegistry(RequestFactory requestFactory) {
        super(new IdentityHashMap<>());
        this.requestFactory = requestFactory;
    }

    @Override
    public void registerDefaults() {
        register(RankedPlayerRestClient.class, new RankedPlayerRestClient(requestFactory));
    }
}