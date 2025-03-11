package net.rankedproject.common.rest.impl;

import net.rankedproject.common.data.domain.RankedPlayer;
import net.rankedproject.common.rest.RestCommunicator;

import java.util.UUID;

public class RankedPlayerRestAccessor extends RestCommunicator<UUID, RankedPlayer> {

    @Override
    protected String getRepository() {
        return "ranked/players";
    }

    @Override
    protected Class<RankedPlayer> getReturnType() {
        return RankedPlayer.class;
    }
}
