package net.rankedproject.privateapi.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "ranked_player")
public class RankedPlayer {

    @Id
    private UUID id;
    private String lastSeenName, ipAddress;

    private int kills, deaths, wins, losses;
}
