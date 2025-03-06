package net.rankedproject.privateapi;

import net.rankedproject.privateapi.controller.RankedPlayer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RankedPlayerRepository extends MongoRepository<RankedPlayer, UUID> {
}
