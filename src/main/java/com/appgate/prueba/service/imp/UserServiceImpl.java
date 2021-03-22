package com.appgate.prueba.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.dto.UserDto;
import com.appgate.prueba.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService {

	

	@Autowired
	private MessageSource messageSource;

	@Override
	public ResponseObject save(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseObject users() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ResponseObject save(UserDto userDto) {
//		ResponseObjectBuilder responseBuilder = new ResponseObjectBuilder(messageSource);
//		try {
//			if (userDto != null) {
//				if (Util.isEmpty(userDto.getName())) {
//					responseBuilder.setDetailedMessage(RouletteCodes.ERROR_VALIDACION_NOMBRES.name());
//					responseBuilder.setRouletteCode(RouletteCodes.ERROR_VALIDACION_NOMBRES);
//				} else if (Util.isEmpty(userDto.getDocument())) {
//					responseBuilder.setDetailedMessage(RouletteCodes.ERROR_NUMERO_DOCUMENTO.name());
//					responseBuilder.setRouletteCode(RouletteCodes.ERROR_NUMERO_DOCUMENTO);
//				} else if (userDto.getPhone() == null) {
//					responseBuilder.setDetailedMessage(RouletteCodes.ERROR_CLIENTE_TELEFONO_NULL.name());
//					responseBuilder.setRouletteCode(RouletteCodes.ERROR_CLIENTE_TELEFONO_NULL);
//				} else if (Util.isEmpty(userDto.getEmail())) {
//					responseBuilder.setDetailedMessage(RouletteCodes.ERROR_CLIENTE_EMAIL_NULL.name());
//					responseBuilder.setRouletteCode(RouletteCodes.ERROR_CLIENTE_EMAIL_NULL);
//				} else if (Util.isEmpty(userDto.getCountry())) {
//					responseBuilder.setDetailedMessage(RouletteCodes.ERROR_ZONA_DESCONOCIDA.name());
//					responseBuilder.setRouletteCode(RouletteCodes.ERROR_ZONA_DESCONOCIDA);
//				} else {
//					if (user == null) {
//					
//						responseBuilder.setDetailedMessage(RouletteCodes.REQUEST_PROCESSED_CORRECTLY.name());
//						responseBuilder.setRouletteCode(RouletteCodes.REQUEST_PROCESSED_CORRECTLY);
//
//					}else {
//						responseBuilder.setDetailedMessage(RouletteCodes.ERROR_USUARIO_EXIST.name());
//						responseBuilder.setRouletteCode(RouletteCodes.ERROR_USUARIO_EXIST);
//					}
//				}
//			}
//		} catch (ExecutionError dke) {
//			responseBuilder.setDetailedMessage(dke.getMessage());
//			responseBuilder.setRouletteCode(RouletteCodes.ERROR_USUARIO);
//
//		}
//
//		return responseBuilder.build();
//	}
//
//	
//
//	@Override
//	public ResponseObject users() {
//		ResponseObjectBuilder responseBuilder = new ResponseObjectBuilder(messageSource);
//		
//		return responseBuilder.build();
//	}

}
