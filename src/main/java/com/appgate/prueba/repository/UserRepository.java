package com.appgate.prueba.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.appgate.prueba.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByDocument(String document);




}
