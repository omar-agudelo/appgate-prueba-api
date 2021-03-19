package com.appgate.prueba.dto;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class UserDto {

	@Id
	private String id;

	private String document;

	private String typeDocuemnto;

	private String name;

	private String address;

	private String country;

	private Integer phone;
	
	private String email;

}