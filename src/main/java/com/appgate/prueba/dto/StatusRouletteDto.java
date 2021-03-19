package com.appgate.prueba.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class StatusRouletteDto {

	private String status;

}