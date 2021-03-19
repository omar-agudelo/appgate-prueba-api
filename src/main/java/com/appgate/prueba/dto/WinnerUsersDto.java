package com.appgate.prueba.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class WinnerUsersDto {

	private String idUser;
	private Integer number;
	private String color;
	private String premium; 

}