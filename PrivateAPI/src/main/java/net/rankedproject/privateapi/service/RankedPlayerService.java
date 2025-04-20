package net.rankedproject.privateapi.service;

import lombok.RequiredArgsConstructor;
import net.rankedproject.privateapi.controller.RankedPlayer;
import net.rankedproject.privateapi.repository.RankedPlayerRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RankedPlayerService {

    private final RankedPlayerRepository repository;

    @Cacheable(value = "rankedPlayers")
    public Flux<RankedPlayer> getAllPlayers() {
        return repository.findAll();
    }

    @Cacheable(value = "rankedPlayers", key = "#id")
    public Mono<RankedPlayer> getPlayerById(UUID id) {
        return repository.findById(id);
    }

    @Cacheable(value = "rankedPlayers")
    public Mono<RankedPlayer> getPlayerByIdOrCreate(UUID id) {
        return repository.findById(id)
                .switchIfEmpty(repository.save(new RankedPlayer(id)));
    }

    @CachePut(value = "rankedPlayers", key = "#player.id")
    public Mono<RankedPlayer> savePlayer(RankedPlayer player) {
        return repository.save(player);
    }

    @CachePut(value = "rankedPlayers", key = "#player.id")
    public Mono<RankedPlayer> updatePlayer(RankedPlayer player) {
        return repository.save(player);
    }

    @CacheEvict(value = "rankedPlayers", key = "#id")
    public Mono<Void> deleteItem(UUID id) {
        return repository.deleteById(id);
    }
}