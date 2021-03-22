package com.appgate.prueba.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

	
	

	private String document;
	
	private String typeDocuemnto;
	
	private String name;
	
	private String address;
	
	private String country;
	
	private Integer phone;
	
	private String email;
}