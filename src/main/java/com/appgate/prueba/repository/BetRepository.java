package com.appgate.prueba.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.appgate.prueba.model.Bet;

@Repository
public interface BetRepository extends MongoRepository<Bet, String> {

	List<Bet> findByIdRoulette(ObjectId idRoulette);

}
