package net.rankedproject.privateapi.controller;

import net.rankedproject.privateapi.service.RankedPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/ranked/players")
public class RankedPlayerController {

    private final RankedPlayerService service;

    @Autowired
    public RankedPlayerController(RankedPlayerService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<RankedPlayer>> getAllPlayers() {
        List<RankedPlayer> allPlayers = service.getAllPlayers();
        return new ResponseEntity<>(allPlayers, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<RankedPlayer> getPlayerById(@PathVariable UUID id) {
        RankedPlayer player = service.getPlayerByIdOrCreate(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<RankedPlayer> savePlayer(@RequestBody RankedPlayer player) {
        RankedPlayer savedPlayer = service.savePlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<RankedPlayer> updatePlayer(@RequestBody RankedPlayer player) {
        RankedPlayer savedPlayer = service.updatePlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable UUID id) {
        service.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}