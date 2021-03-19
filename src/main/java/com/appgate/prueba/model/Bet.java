package com.appgate.prueba.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Document(collection = "BET")
public class Bet {

	@Id
	private ObjectId id;
	private ObjectId user;
	private Integer number;
	private String color;
	private ObjectId idRoulette;
	private Integer amount;
	
}