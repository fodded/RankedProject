package net.rankedproject.common.config;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;
import net.rankedproject.common.config.loader.ConfigLoader;
import net.rankedproject.common.config.loader.impl.FileConfigLoader;
import net.rankedproject.common.config.parser.impl.YamlConfigParser;
import org.jetbrains.annotations.NotNull;

@Singleton
@RequiredArgsConstructor(onConstructor_={@Inject})
public class TestConfig implements Config {

    private final Injector injector;

    @NotNull
    @Override
    public String getName() {
        return "test-config.json";
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public YamlConfigParser getParser() {
        return injector.getInstance(YamlConfigParser.class);
    }

    @Override
    public ConfigLoader getConfigLoader() {
        return injector.getInstance(FileConfigLoader.class);
    }
}
