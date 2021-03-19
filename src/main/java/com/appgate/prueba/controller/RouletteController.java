package com.appgate.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.dto.ToBetDto;
import com.appgate.prueba.service.RouletteService;
import com.appgate.prueba.util.ConstantsRoulette;

@RestController
@RequestMapping("/v1/roulette")
@CrossOrigin(origins = "*")
public class RouletteController {

	@Autowired
	private RouletteService rouletteService;

	@PostMapping
	@ResponseBody
	public ResponseEntity<ResponseObject> save() {
		return ResponseEntity.status(HttpStatus.OK).body(rouletteService.save());
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<ResponseObject> findRoulette(
			@RequestHeader(value = ConstantsRoulette.IDROULETTE, required = true) String idroulette) {
		return ResponseEntity.status(HttpStatus.OK).body(rouletteService.findRouletteById(idroulette));
	}

	@PutMapping
	@ResponseBody
	public ResponseEntity<ResponseObject> toBet(
			@RequestHeader(value = ConstantsRoulette.IDCLIENT, required = true) String idClient,
			@RequestBody ToBetDto toBetDto) {
		return ResponseEntity.status(HttpStatus.OK).body(rouletteService.toBet(idClient, toBetDto));
	}

	@GetMapping("/close")
	@ResponseBody
	public ResponseEntity<ResponseObject> clouse(
			@RequestHeader(value = ConstantsRoulette.IDROULETTE, required = true) String idRoulette) {
		return ResponseEntity.status(HttpStatus.OK).body(rouletteService.close(idRoulette));
	}
	
	
	@GetMapping("/roulettes")
	@ResponseBody
	public ResponseEntity<ResponseObject> roulettes() {
		return ResponseEntity.status(HttpStatus.OK).body(rouletteService.roulettes());
	}
	
}
