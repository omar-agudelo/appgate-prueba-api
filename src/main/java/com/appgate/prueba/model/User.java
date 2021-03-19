package com.appgate.prueba.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Document(collection = "USER")
public class User {

	@Id
	private ObjectId id;

	private String document;
	
	private String typeDocuemnto;
	
	private String name;
	
	private String address;
	
	private String country;
	
	private Integer phone;
	
	private String email;
}