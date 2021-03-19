package com.appgate.prueba.service;


import org.springframework.web.multipart.MultipartFile;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.dto.ToBetDto;


public interface UploadFileService {

	


	public ResponseObject uploadFile(MultipartFile file);




}
