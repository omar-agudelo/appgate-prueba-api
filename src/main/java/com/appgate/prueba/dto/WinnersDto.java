package com.appgate.prueba.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class WinnersDto {

	private String idRoulette;
	private String status;
	private Integer winnerNumber;
	private String color;
	private Integer countWinners;
	private List<WinnerUsersDto> winnerUsers;

}