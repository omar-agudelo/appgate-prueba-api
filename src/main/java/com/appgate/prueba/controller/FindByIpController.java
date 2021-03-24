package com.appgate.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.service.IpService;
import com.appgate.prueba.util.ConstantsRoulette;

@RestController
@RequestMapping("/v1/findip")
@CrossOrigin("*")
public class FindByIpController {

	@Autowired
	private IpService ipService;


	@GetMapping
	@ResponseBody
	public ResponseEntity<ResponseObject> findByip(@RequestHeader(value = ConstantsRoulette.IP, required = true) String ip) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ipService.findByip(ip));
	}



}
