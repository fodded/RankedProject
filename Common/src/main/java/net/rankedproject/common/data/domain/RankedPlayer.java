package net.rankedproject.common.data.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RankedPlayer extends BasePlayer {
    private String lastSeenName, ipAddress;
    private int kills, deaths, wins, losses;
    private long dbVersion;
}