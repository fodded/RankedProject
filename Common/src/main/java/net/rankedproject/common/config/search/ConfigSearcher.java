package net.rankedproject.common.config.search;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class ConfigSearcher {

    private final ConfigSearchOption searchOption;

    @NotNull
    public String get() {
        var path = searchOption.path();
        var configType = searchOption.configType();
        var placeholders = searchOption.placeholders();

        var configAccessor = searchOption.configAccessor();
        var configLoader = searchOption.configLoader();

        var config = configAccessor.get(configType, configLoader);
        return null;
    }
}