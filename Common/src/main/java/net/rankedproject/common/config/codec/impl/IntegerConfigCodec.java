package net.rankedproject.common.config.codec.impl;

import com.google.common.base.Preconditions;
import net.rankedproject.common.config.codec.ConfigCodec;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IntegerConfigCodec implements ConfigCodec<Integer, Object> {

    @NotNull
    @Override
    public Integer parse(@NotNull Object serialized) {
        Preconditions.checkArgument(serialized instanceof Integer, "Couldn't parse %s, the value is not Integer".formatted(serialized));
        return (Integer) serialized;
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> parseList(@NotNull Object serialized) {
        Preconditions.checkArgument(serialized instanceof List, "Couldn't parse %s, the value is not Integer".formatted(serialized));
        return (List<Integer>) serialized;
    }
}
