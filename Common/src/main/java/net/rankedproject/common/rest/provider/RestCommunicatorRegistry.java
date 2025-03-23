package net.rankedproject.common.rest.provider;

import net.rankedproject.common.registry.BaseRegistry;
import net.rankedproject.common.rest.RestClient;
import net.rankedproject.common.rest.impl.RankedPlayerRestClient;

import java.util.IdentityHashMap;

public class RestCommunicatorRegistry extends BaseRegistry<Class<?>, RestClient<?, ?>> {

    public RestCommunicatorRegistry() {
        super(new IdentityHashMap<>());
    }

    @Override
    public void registerDefaults() {
        register(RankedPlayerRestClient.class, new RankedPlayerRestClient());
    }
}
