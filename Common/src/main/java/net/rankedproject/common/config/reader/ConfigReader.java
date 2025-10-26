package net.rankedproject.common.config.reader;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;
import net.rankedproject.common.config.accessor.ConfigAccessor;
import net.rankedproject.common.config.parser.ConfigParser;
import net.rankedproject.common.config.parser.ParsedConfig;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class ConfigReader {

    private final ConfigAccessor configAccessor;
    private final Injector injector;

    @NotNull
    @SuppressWarnings("unchecked")
    public <R, U> R get(Class<R> returnType, @NotNull ConfigReadOption readOption) {
        var config = injector.getInstance(readOption.configType());

        ParsedConfig<U> parsedConfig = configAccessor.get(readOption);
        var metadata = config.getMetadata();

        ConfigParser<U> parser = (ConfigParser<U>) metadata.parser();
        var line = parser.get(readOption.path(), parsedConfig, returnType);

        if (returnType == String.class) {
            line = (R) applyPlaceholder((String) line, readOption);
        }

        return line;
    }

    @NotNull
    @SuppressWarnings("unchecked")
    public <R, U> List<? extends R> getAsList(Class<R> returnType, @NotNull ConfigReadOption readOption) {
        var config = injector.getInstance(readOption.configType());

        ParsedConfig<U> parsedConfig = configAccessor.get(readOption);
        var metadata = config.getMetadata();

        ConfigParser<U> parser = (ConfigParser<U>) metadata.parser();
        var list = parser.getAsList(readOption.path(), parsedConfig, returnType);

        if (returnType == String.class) {
            list = (List<? extends R>) list.stream()
                    .map(line -> applyPlaceholder((String) line, readOption))
                    .toList();
        }

        return list;
    }

    @NotNull
    public Integer getAsInt(@NotNull ConfigReadOption readOption) {
        return get(Integer.class, readOption);
    }

    @NotNull
    public String getAsString(@NotNull ConfigReadOption readOption) {
        return get(String.class, readOption);
    }

    @NotNull
    private String applyPlaceholder(@NotNull String line, @NotNull ConfigReadOption readOption) {
        var result = line;
        for (var placeholder : readOption.placeholders()) {
            result = line.replace(placeholder.placeholder(), placeholder.value());
        }
        return result;
    }
}
