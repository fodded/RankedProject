package net.rankedproject.common.config;

import lombok.Builder;
import net.rankedproject.common.config.loader.ConfigLoader;
import net.rankedproject.common.config.parser.ConfigParser;

@Builder
public record ConfigMetadata(ConfigParser<?> parser, ConfigLoader loader, int version, String name) {
}