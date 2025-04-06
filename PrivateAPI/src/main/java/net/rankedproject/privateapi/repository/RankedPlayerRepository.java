package net.rankedproject.privateapi.repository;

import net.rankedproject.privateapi.controller.RankedPlayer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RankedPlayerRepository extends R2dbcRepository<RankedPlayer, UUID> {

}