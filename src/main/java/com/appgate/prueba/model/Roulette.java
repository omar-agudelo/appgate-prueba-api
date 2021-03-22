package com.appgate.prueba.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Roulette {
	/**
	 * Identificador de la línea
	 */

	private String name;
	private String status;
	private Integer winner;
	private String color;


}