package net.rankedproject.common.rest.impl;

import net.rankedproject.common.data.domain.RankedPlayer;
import net.rankedproject.common.rest.type.PlayerRestClient;

public class RankedPlayerRestClient extends PlayerRestClient<RankedPlayer> {

    @Override
    public String getRepository() {
        return "ranked/players";
    }

    @Override
    public Class<RankedPlayer> getReturnType() {
        return RankedPlayer.class;
    }
}
