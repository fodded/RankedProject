package net.rankedproject.common.config.search;

import lombok.Getter;
import net.rankedproject.common.config.Config;
import net.rankedproject.common.config.accessor.ConfigAccessor;
import net.rankedproject.common.config.loader.ConfigLoader;
import net.rankedproject.common.config.placeholder.ConfigPlaceholder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public record ConfigSearchOption(String path,
                                 Class<? extends Config> configType,
                                 ConfigLoader configLoader,
                                 ConfigAccessor configAccessor,
                                 List<ConfigPlaceholder> placeholders) {

    public static ConfigSearchOption.Builder builder() {
        return new ConfigSearchOption.Builder();
    }

    @Getter
    public static class Builder {

        private String path;
        private Class<? extends Config> configType;

        private ConfigLoader configLoader;
        private ConfigAccessor configAccessor;

        private final List<ConfigPlaceholder> placeholders = new ArrayList<>();

        @NotNull
        public Builder path(@NotNull String path) {
            this.path = path;
            return this;
        }

        @NotNull
        public Builder config(@NotNull Class<? extends Config> configType) {
            this.configType = configType;
            return this;
        }

        public Builder configLoader(@NotNull ConfigLoader configLoader) {
            this.configLoader = configLoader;
            return this;
        }

        public Builder configAccessor(@NotNull ConfigAccessor configAccessor) {
            this.configAccessor = configAccessor;
            return this;
        }

        public ConfigSearcher placeholder(@NotNull String placeholder, @NotNull String value) {
            placeholders.add(new ConfigPlaceholder(placeholder, value));
            return new ConfigSearcher(build());
        }

        public ConfigSearchOption build() {
            return new ConfigSearchOption(path, configType, configLoader, configAccessor, placeholders);
        }
    }
}
