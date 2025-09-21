package net.rankedproject.spigot.logger;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import net.rankedproject.spigot.CommonPlugin;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class BukkitLogger {

    private final CommonPlugin plugin;

    public void info(String message) {
        var logger = plugin.getLogger();
        logger.info(message);
    }

    public void warning(String message) {
        var logger = plugin.getLogger();
        logger.warning(message);
    }
}
