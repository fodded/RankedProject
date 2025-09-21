package net.rankedproject.common.config;

import org.jetbrains.annotations.NotNull;

public class TestConfig implements Config {

    @NotNull
    @Override
    public String name() {
        return "test-config";
    }

    @Override
    public int version() {
        return 1;
    }
}
