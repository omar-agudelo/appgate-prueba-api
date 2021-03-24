package com.appgate.prueba.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.config.ResponseObjectBuilder;
import com.appgate.prueba.dto.FileDto;
import com.appgate.prueba.model.File;
import com.appgate.prueba.repository.FileUploadRepository;
import com.appgate.prueba.service.IpService;
import com.appgate.prueba.util.RouletteCodes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements IpService {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private FileUploadRepository fileUploadRepository;

	@Override
	public ResponseObject findByip(String ip) {
		ResponseObjectBuilder responseBuilder = new ResponseObjectBuilder(messageSource);
		long result = 0;
		String[] ipAddressInArray = ip.split("\\.");
		for (int i = 0; i < ipAddressInArray.length; i++) {
			int power = 3 - i;
			result += (Integer.parseInt(ipAddressInArray[i]) % 256 * Math.pow(256, power));
		}
		List<File> files = fileUploadRepository.findByIPFromAndIPTo(String.valueOf(result));
		if (!files.isEmpty()) {
			responseBuilder.setRouletteCode(RouletteCodes.REQUEST_PROCESSED_CORRECTLY);
			responseBuilder.setDetailedMessage(RouletteCodes.REQUEST_PROCESSED_CORRECTLY.name());
			responseBuilder.addPayloadProperty("Respuesta", returnListDto(files));
		} else {
			responseBuilder.setRouletteCode(RouletteCodes.REQUEST_NOT_CORRECTLY);
			responseBuilder.setDetailedMessage(RouletteCodes.REQUEST_NOT_CORRECTLY.name());
		}
		return responseBuilder.build();
	}

	private List<FileDto> returnListDto(List<File> files) {
		List<FileDto> fileDtos = new ArrayList<>();
		files.stream().filter(f -> fileDtos.add(dto(f))).collect(Collectors.toList());
		return fileDtos;
	}

	private FileDto dto(File f) {
		return new FileDto(f.getCountryCode(), f.getRegion(), f.getCity(), f.getTimeZone());
	}

}
