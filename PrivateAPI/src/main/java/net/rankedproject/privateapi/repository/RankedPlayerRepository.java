package net.rankedproject.privateapi.repository;

import net.rankedproject.privateapi.controller.RankedPlayer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface RankedPlayerRepository extends R2dbcRepository<RankedPlayer, UUID> {

    @Query("INSERT INTO ")
    Mono<RankedPlayer> save(RankedPlayer entity);

    @Query("")
    Mono<RankedPlayer> update(RankedPlayer entity);
}