package net.rankedproject.privateapi.service;

import net.rankedproject.privateapi.controller.RankedPlayer;
import net.rankedproject.privateapi.repository.RankedPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RankedPlayerService {

    private final RankedPlayerRepository repository;

    @Autowired
    public RankedPlayerService(RankedPlayerRepository repository) {
        this.repository = repository;
    }

    public List<RankedPlayer> getAllPlayers() {
        return repository.findAll();
    }

    public Optional<RankedPlayer> getPlayerById(UUID id) {
        return repository.findById(id);
    }

    public RankedPlayer getPlayerByIdOrCreate(UUID id) {
        RankedPlayer player = repository.findById(id).orElseGet(() -> {
            RankedPlayer rankedPlayer = new RankedPlayer();
            rankedPlayer.setId(id);
            return rankedPlayer;
        });
        return savePlayer(player);
    }

    public RankedPlayer savePlayer(RankedPlayer player) {
        return repository.save(player);
    }

    public RankedPlayer updatePlayer(RankedPlayer player) {
        return repository.save(player);
    }

    public void deleteItem(UUID id) {
        repository.deleteById(id);
    }
}