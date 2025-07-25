package net.rankedproject.privateapi.controller;

import lombok.RequiredArgsConstructor;
import net.rankedproject.privateapi.service.RankedPlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/ranked/players")
@RequiredArgsConstructor
public class RankedPlayerController {

    private final RankedPlayerService service;

    @GetMapping
    public Flux<ResponseEntity<RankedPlayer>> getAllPlayers() {
        return service.getAllPlayers()
                .map(players -> new ResponseEntity<>(players, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public Mono<ResponseEntity<RankedPlayer>> getPlayerById(@PathVariable UUID id) {
        return service.getPlayerByIdOrCreate(id)
                .map(player -> new ResponseEntity<>(player, HttpStatus.OK));
    }

    @PostMapping
    public Mono<ResponseEntity<RankedPlayer>> savePlayer(@RequestBody RankedPlayer player) {
        return service.savePlayer(player)
                .map(savedPlayer -> new ResponseEntity<>(savedPlayer, HttpStatus.CREATED));
    }

    @PutMapping(path = "/{id}")
    public Mono<ResponseEntity<RankedPlayer>> updatePlayer(@RequestBody RankedPlayer player) {
        return service.updatePlayer(player)
                .map(updatedPlayer -> new ResponseEntity<>(updatedPlayer, HttpStatus.CREATED));
    }

    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<Void>> deletePlayer(@PathVariable UUID id) {
        return service.deleteItem(id)
                .then(Mono.fromCallable(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }
}