package net.rankedproject.privateapi.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.InetAddress;
import java.util.UUID;

@Data
@Document
@RequiredArgsConstructor
public class RankedPlayer {

    @Id
    private final UUID id;

    private InetAddress ipAddress;
    private String lastSeenName;

    private int kills, deaths, wins, losses;
}
