package com.appgate.prueba.service;


import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.dto.ToBetDto;


public interface RouletteService {

	
	public ResponseObject save();

	public ResponseObject findRouletteById(String idroulette);

	public ResponseObject toBet(String idClient, ToBetDto toBetDto);

	public ResponseObject close(String idRoulette);

	public ResponseObject roulettes();




}
