package net.rankedproject.privateapi.controller;

import lombok.RequiredArgsConstructor;
import net.rankedproject.privateapi.service.RankedPlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/ranked/players")
@RequiredArgsConstructor
public class RankedPlayerController {

    private final RankedPlayerService service;

    @GetMapping
    public ResponseEntity<List<RankedPlayer>> getAllPlayers() {
        List<RankedPlayer> allPlayers = service.getAllPlayers();
        return new ResponseEntity<>(allPlayers, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RankedPlayer> getPlayerById(@PathVariable UUID id) {
        RankedPlayer player = service.getPlayerByIdOrCreate(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RankedPlayer> savePlayer(@RequestBody RankedPlayer player) {
        RankedPlayer savedPlayer = service.savePlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RankedPlayer> updatePlayer(@RequestBody RankedPlayer player) {
        RankedPlayer savedPlayer = service.updatePlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable UUID id) {
        service.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}