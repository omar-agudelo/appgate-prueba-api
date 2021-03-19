package com.appgate.prueba.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.appgate.prueba.model.Roulette;

@Repository
public interface RouletteRepository extends MongoRepository<Roulette, String> {

	@Query("{ 'status':?0 }")
	List<Roulette> findByStatus(String name);

}
