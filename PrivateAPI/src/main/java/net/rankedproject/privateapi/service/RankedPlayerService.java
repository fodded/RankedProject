package net.rankedproject.privateapi.service;

import lombok.RequiredArgsConstructor;
import net.rankedproject.privateapi.controller.RankedPlayer;
import net.rankedproject.privateapi.repository.RankedPlayerRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RankedPlayerService {

    private final RankedPlayerRepository repository;

    @Cacheable(value = "rankedPlayers")
    public List<RankedPlayer> getAllPlayers() {
        return repository.findAll();
    }

    @Cacheable(value = "rankedPlayers", key = "#id")
    public Optional<RankedPlayer> getPlayerById(UUID id) {
        return repository.findById(id);
    }

    @Cacheable(value = "rankedPlayers")
    public RankedPlayer getPlayerByIdOrCreate(UUID id) {
        RankedPlayer player = repository.findById(id).orElseGet(() -> {
            RankedPlayer rankedPlayer = new RankedPlayer();
            rankedPlayer.setId(id);
            return rankedPlayer;
        });
        return savePlayer(player);
    }

    @CachePut(value = "rankedPlayers", key = "#player.id")
    public RankedPlayer savePlayer(RankedPlayer player) {
        return repository.save(player);
    }

    @CachePut(value = "rankedPlayers", key = "#player.id")
    public RankedPlayer updatePlayer(RankedPlayer player) {
        return repository.save(player);
    }

    @CacheEvict(value = "rankedPlayers", key = "#id")
    public void deleteItem(UUID id) {
        repository.deleteById(id);
    }
}