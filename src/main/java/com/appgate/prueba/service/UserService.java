package com.appgate.prueba.service;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.dto.UserDto;

public interface UserService {
	
	ResponseObject save(UserDto userDto);

	ResponseObject users();

}
