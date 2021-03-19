package com.appgate.prueba.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.config.ResponseObjectBuilder;
import com.appgate.prueba.dto.RouletteDto;
import com.appgate.prueba.dto.StatusRouletteDto;
import com.appgate.prueba.dto.ToBetDto;
import com.appgate.prueba.dto.WinnerUsersDto;
import com.appgate.prueba.dto.WinnersDto;
import com.appgate.prueba.model.Bet;
import com.appgate.prueba.model.Roulette;
import com.appgate.prueba.repository.BetRepository;
import com.appgate.prueba.repository.RouletteRepository;
import com.appgate.prueba.service.UploadFileService;
import com.appgate.prueba.util.ConstantsRoulette;
import com.appgate.prueba.util.RouletteCodes;
import com.appgate.prueba.util.Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "rouletteService")
public class RouletteServiceImpl implements UploadFileService {

	@Autowired
	private RouletteRepository rouletteRepository;

	@Autowired
	private BetRepository betRepository;

	@Autowired
	private MessageSource messageSource;

	
	
	

	@Override
	public ResponseObject uploadFile(MultipartFile file) {
		ResponseObjectBuilder responseBuilder = new ResponseObjectBuilder(messageSource);
		
		
		responseBuilder.setRouletteCode(RouletteCodes.REQUEST_PROCESSED_CORRECTLY);
		responseBuilder.setDetailedMessage(RouletteCodes.REQUEST_PROCESSED_CORRECTLY.name());
		return null;
	}
}
