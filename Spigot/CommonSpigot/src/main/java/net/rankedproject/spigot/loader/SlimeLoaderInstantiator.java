package net.rankedproject.spigot.loader;

import com.infernalsuite.asp.api.loaders.SlimeLoader;
import com.infernalsuite.asp.loaders.mysql.MysqlLoader;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SlimeLoaderInstantiator implements Instantiator<SlimeLoader> {

    private static final String SQL_URL = Optional
            .ofNullable(System.getenv("SQL_URL"))
            .orElseThrow();

    private static final String SQL_HOST = Optional
            .ofNullable(System.getenv("SQL_HOST"))
            .orElseThrow();

    private static final String SQL_DATABASE = Optional
            .ofNullable(System.getenv("SQL_DATABASE"))
            .orElseThrow();

    private static final int SQL_PORT = Optional
            .ofNullable(System.getenv("SQL_PORT"))
            .map(Integer::parseInt)
            .orElseThrow();

    private static final boolean SQL_USE_SSL = Optional
            .ofNullable(System.getenv("SQL_USE_SSL"))
            .map(Boolean::parseBoolean)
            .orElse(false);

    private static final String SQL_USERNAME = Optional
            .ofNullable(System.getenv("SQL_USERNAME"))
            .orElse("");

    private static final String SQL_PASSWORD = Optional
            .ofNullable(System.getenv("SQL_PASSWORD"))
            .orElse("");

    private SlimeLoader slimeLoader;

    @NotNull
    @Override
    @SneakyThrows
    public SlimeLoader init() {
        slimeLoader = new MysqlLoader(SQL_URL, SQL_HOST, SQL_PORT, SQL_DATABASE, SQL_USE_SSL, SQL_USERNAME, SQL_PASSWORD);
        return slimeLoader;
    }

    @NotNull
    @Override
    public SlimeLoader get() {
        return slimeLoader;
    }
}
