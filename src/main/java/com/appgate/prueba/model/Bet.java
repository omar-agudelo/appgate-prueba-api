package com.appgate.prueba.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Bet {


	private Integer number;
	private String color;
	private Integer amount;
	
}