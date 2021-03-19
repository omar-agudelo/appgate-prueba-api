package com.appgate.prueba.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Document(collection = "ROULETTE")
public class Roulette {
	/**
	 * Identificador de la línea
	 */
	@Id
	private ObjectId id;
	private String name;
	private String status;
	private Integer winner;
	private String color;


}