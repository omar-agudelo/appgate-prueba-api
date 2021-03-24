package com.appgate.prueba.service;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.dto.UserDto;

public interface IpService {
	
	
	ResponseObject findByip(String ip);

}
