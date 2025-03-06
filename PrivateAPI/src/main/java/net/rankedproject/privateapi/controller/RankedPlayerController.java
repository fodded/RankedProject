package net.rankedproject.privateapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/rankedplayer")
public class RankedPlayerController {

    @GetMapping
    public RankedPlayer getPlayer() {
        return new RankedPlayer(UUID.randomUUID());
    }
}