package net.rankedproject.common.data.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class RankedPlayer {
    private UUID id;
    private String lastSeenName, ipAddress;

    private int kills, deaths, wins, losses;
}