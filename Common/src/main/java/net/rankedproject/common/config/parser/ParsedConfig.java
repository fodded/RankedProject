package net.rankedproject.common.config.parser;

public record ParsedConfig<T>(T data, Class<T> dataType) {
}
