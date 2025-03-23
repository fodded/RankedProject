package net.rankedproject.common.data.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RankedPlayer extends BasePlayer {
    private String lastSeenName, ipAddress;
    private int kills, deaths, wins, losses;
}