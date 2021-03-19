package com.appgate.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.service.UploadFileService;

@RestController
@RequestMapping("/v1/upload")
@CrossOrigin(origins = "*")
public class UploadFileController {

	@Autowired
	private UploadFileService uploadFileService;

	@PostMapping()
	public ResponseEntity<ResponseObject> pruebaLocal(@RequestPart(value = "file") MultipartFile file) {
		return ResponseEntity.status(HttpStatus.OK).body(uploadFileService.uploadFile(file));
	}

}
