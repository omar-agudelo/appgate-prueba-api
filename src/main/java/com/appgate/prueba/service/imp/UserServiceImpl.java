package com.appgate.prueba.service.imp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.config.ResponseObjectBuilder;
import com.appgate.prueba.dto.UserDto;
import com.appgate.prueba.model.User;
import com.appgate.prueba.repository.UserRepository;
import com.appgate.prueba.service.UserService;
import com.appgate.prueba.util.RouletteCodes;
import com.appgate.prueba.util.Util;
import com.google.common.util.concurrent.ExecutionError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messageSource;

	@Override
	public ResponseObject save(UserDto userDto) {
		ResponseObjectBuilder responseBuilder = new ResponseObjectBuilder(messageSource);
		try {
			if (userDto != null) {
				if (Util.isEmpty(userDto.getName())) {
					responseBuilder.setDetailedMessage(RouletteCodes.ERROR_VALIDACION_NOMBRES.name());
					responseBuilder.setRouletteCode(RouletteCodes.ERROR_VALIDACION_NOMBRES);
				} else if (Util.isEmpty(userDto.getDocument())) {
					responseBuilder.setDetailedMessage(RouletteCodes.ERROR_NUMERO_DOCUMENTO.name());
					responseBuilder.setRouletteCode(RouletteCodes.ERROR_NUMERO_DOCUMENTO);
				} else if (userDto.getPhone() == null) {
					responseBuilder.setDetailedMessage(RouletteCodes.ERROR_CLIENTE_TELEFONO_NULL.name());
					responseBuilder.setRouletteCode(RouletteCodes.ERROR_CLIENTE_TELEFONO_NULL);
				} else if (Util.isEmpty(userDto.getEmail())) {
					responseBuilder.setDetailedMessage(RouletteCodes.ERROR_CLIENTE_EMAIL_NULL.name());
					responseBuilder.setRouletteCode(RouletteCodes.ERROR_CLIENTE_EMAIL_NULL);
				} else if (Util.isEmpty(userDto.getCountry())) {
					responseBuilder.setDetailedMessage(RouletteCodes.ERROR_ZONA_DESCONOCIDA.name());
					responseBuilder.setRouletteCode(RouletteCodes.ERROR_ZONA_DESCONOCIDA);
				} else {
					User user = userRepository.findByDocument(userDto.getDocument());
					if (user == null) {
						responseBuilder.addPayloadProperty(RouletteCodes.USER.name(),
								returUser(userRepository.save(new User().setCountry(userDto.getCountry())
										.setDocument(userDto.getDocument()).setName(userDto.getName())
										.setPhone(userDto.getPhone()).setTypeDocuemnto(userDto.getTypeDocuemnto())
										.setAddress(userDto.getAddress()).setEmail(userDto.getEmail()))));
						responseBuilder.setDetailedMessage(RouletteCodes.REQUEST_PROCESSED_CORRECTLY.name());
						responseBuilder.setRouletteCode(RouletteCodes.REQUEST_PROCESSED_CORRECTLY);

					}else {
						responseBuilder.setDetailedMessage(RouletteCodes.ERROR_USUARIO_EXIST.name());
						responseBuilder.setRouletteCode(RouletteCodes.ERROR_USUARIO_EXIST);
					}
				}
			}
		} catch (ExecutionError dke) {
			responseBuilder.setDetailedMessage(dke.getMessage());
			responseBuilder.setRouletteCode(RouletteCodes.ERROR_USUARIO);

		}

		return responseBuilder.build();
	}

	private UserDto returUser(User user) {
		return new UserDto().setId(user.getId().toHexString()).setCountry(user.getCountry())
				.setDocument(user.getDocument()).setName(user.getName()).setPhone(user.getPhone())
				.setTypeDocuemnto(user.getTypeDocuemnto()).setAddress(user.getAddress()).setEmail(user.getEmail());
	}

	@Override
	public ResponseObject users() {
		ResponseObjectBuilder responseBuilder = new ResponseObjectBuilder(messageSource);
		List<User> users = userRepository.findAll();
		if (!users.isEmpty()) {
			responseBuilder.setDetailedMessage(RouletteCodes.REQUEST_PROCESSED_CORRECTLY.name());
			responseBuilder.setRouletteCode(RouletteCodes.REQUEST_PROCESSED_CORRECTLY);
			responseBuilder.addPayloadProperty(RouletteCodes.USER.name(), users.stream()
					.map(e -> Stream.of(returUser(e)).collect(Collectors.toList())).collect(Collectors.toList()));
		} else {
			responseBuilder.setDetailedMessage(RouletteCodes.ERROR_USUARIO.name());
			responseBuilder.setRouletteCode(RouletteCodes.ERROR_USUARIO);
		}
		return responseBuilder.build();
	}

}
