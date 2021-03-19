package com.appgate.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.dto.UserDto;
import com.appgate.prueba.service.UserService;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping
	@ResponseBody
	public ResponseEntity<ResponseObject> save(@RequestBody UserDto userDto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.save(userDto));
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<ResponseObject> users() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.users());
	}



}
