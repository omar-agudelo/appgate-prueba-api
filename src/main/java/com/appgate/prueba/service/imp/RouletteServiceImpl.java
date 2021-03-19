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
import com.appgate.prueba.service.RouletteService;
import com.appgate.prueba.util.ConstantsRoulette;
import com.appgate.prueba.util.RouletteCodes;
import com.appgate.prueba.util.Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "rouletteService")
public class RouletteServiceImpl implements RouletteService {

	@Autowired
	private RouletteRepository rouletteRepository;

	@Autowired
	private BetRepository betRepository;

	@Autowired
	private MessageSource messageSource;

	@Override
	public ResponseObject save() {
		log.info(ConstantsRoulette.INITSAVE);
		int color = (int) (Math.random() * 2);
		Roulette roulette = rouletteRepository.save(new Roulette().setName(Util.getRandomName())
				.setStatus(RouletteCodes.ACTIVE.name()).setWinner((int) (Math.random() * 36))
				.setColor(color != 2 ? messageSorce(RouletteCodes.RED.name())
						: messageSorce(RouletteCodes.BLACK.name())));
		ResponseObjectBuilder responseBuilder = request();
		responseBuilder.addPayloadProperty(RouletteCodes.ROULETTE.name(), new RouletteDto()
				.setId(roulette.getId().toHexString()).setName(roulette.getName()).setStatus(roulette.getStatus()));
		log.info(ConstantsRoulette.FINSAVE);
		return responseBuilder.build();
	}

	@Override
	public ResponseObject findRouletteById(String idRoulette) {
		log.info(ConstantsRoulette.INITFINE + ConstantsRoulette.EJECUTOR, idRoulette);
		ResponseObjectBuilder responseBuilder = null;
		Optional<Roulette> roulette = findRouletteId(idRoulette);
		if (roulette.isPresent()) {
			responseBuilder = request();
			responseBuilder.addPayloadProperty(RouletteCodes.ROULETTE.name(),
					new StatusRouletteDto().setStatus(roulette.get().getStatus()));
		} else {
			responseBuilder = request();
			responseBuilder.addPayloadProperty(RouletteCodes.ROULETTE.name(),
					new StatusRouletteDto().setStatus(messageSorce(RouletteCodes.INACTIVE.name())));
		}
		log.info(ConstantsRoulette.FINfIND);
		return responseBuilder.build();
	}

	@Override
	public ResponseObject toBet(String idClient, ToBetDto toBetDto) {
		log.info(ConstantsRoulette.INITTOBET+ConstantsRoulette.EJECUTOR, toBetDto);
		ResponseObjectBuilder responseBuilder = rouletteServiceImpl();
		if (toBetDto != null) {
			if (toBetDto.getNumber() >= 0 && toBetDto.getNumber() <= 36) {
				if (toBetDto.getColor() != null && toBetDto.getColor().equals(messageSorce(RouletteCodes.RED.name()))
						|| toBetDto.getColor() != null
								&& toBetDto.getColor().equals(messageSorce(RouletteCodes.BLACK.name()))) {
					responseBuilder = amount(toBetDto, idClient);
				} else {
					responseBuilder.setRouletteCode(RouletteCodes.ERROR_COLOR_NULL);
					responseBuilder.setDetailedMessage(RouletteCodes.ERROR_COLOR_NULL.name());
				}

			} else {
				responseBuilder.setRouletteCode(RouletteCodes.ERROR_PETITION_NULL);
				responseBuilder.setDetailedMessage(RouletteCodes.ERROR_PETITION_NULL.name());
			}
		} else {
			responseBuilder.setRouletteCode(RouletteCodes.ERROR_BET_NUMBER);
			responseBuilder.setDetailedMessage(RouletteCodes.ERROR_BET_NUMBER.name());
		}
		log.info(ConstantsRoulette.FINTOBET);
		return responseBuilder.build();
	}

	

	@Override
	public ResponseObject roulettes() {
		log.info(ConstantsRoulette.INITROULETTES);
		ResponseObjectBuilder responseBuilder = rouletteServiceImpl();
		List<Roulette> roulettes = rouletteRepository.findAll();
		if (!Util.isEmpty(roulettes)) {
			responseBuilder = request();
			responseBuilder.addPayloadProperty(RouletteCodes.ROULETTE.name(),
					roulettes.stream().map(roulette -> Stream.of(roulettes(roulette)).collect(Collectors.toList()))
							.collect(Collectors.toList()));

		} else {
			responseBuilder = request();
			responseBuilder.addPayloadProperty(RouletteCodes.ERROR_NOT_BETS.name(),
					new StatusRouletteDto().setStatus(messageSorce(RouletteCodes.ERROR_NOT_BETS.name())));
		}
		return responseBuilder.build();
	}

	

	@Override
	public ResponseObject close(String idRoulette) {
		log.info(ConstantsRoulette.CLOUSE);
		ResponseObjectBuilder responseBuilder = rouletteServiceImpl();
		Optional<Roulette> roulette = findRouletteId(idRoulette);
		if (roulette.isPresent()) {
			List<Bet> bets = betRepository.findByIdRoulette(new ObjectId(idRoulette));
			if (!Util.isEmpty(bets)) {
				responseBuilder = functionBest(bets, roulette.get());
			} else {
				responseBuilder = request();
				responseBuilder.addPayloadProperty(RouletteCodes.ERROR_NOT_BETS.name(),
						new StatusRouletteDto().setStatus(messageSorce(RouletteCodes.ERROR_NOT_BETS.name())));
			}
		} else {
			responseBuilder = request();
			responseBuilder.addPayloadProperty(RouletteCodes.ROULETTE.name(),
					new StatusRouletteDto().setStatus(messageSorce(RouletteCodes.INACTIVE.name())));
		}
		return responseBuilder.build();
	}

	
	private ResponseObjectBuilder amount(ToBetDto toBetDto, String idClient) {
		ResponseObjectBuilder responseBuilder = rouletteServiceImpl();
		if (toBetDto.getAmount() <= ConstantsRoulette.AMOUNT) {
			Optional<Roulette> roulette = findRouletteId(toBetDto.getIdRoulette());
			if (roulette.isPresent()) {
				betRepository.save(new Bet().setAmount(toBetDto.getAmount()).setColor(toBetDto.getColor())
						.setIdRoulette(roulette.get().getId()).setNumber(toBetDto.getNumber())
						.setUser(new ObjectId(idClient)));
				responseBuilder = request();
				responseBuilder.addPayloadProperty(RouletteCodes.THANKS_BET.name(),
						messageSorce(RouletteCodes.THANKS_BET.name()));
			} else {
				responseBuilder.setRouletteCode(RouletteCodes.ERROR_AMOUNT);
				responseBuilder.setDetailedMessage(RouletteCodes.ERROR_AMOUNT.name());
			}
		} else {
			responseBuilder.setRouletteCode(RouletteCodes.ERROR_AMOUNT);
			responseBuilder.setDetailedMessage(RouletteCodes.ERROR_AMOUNT.name());
		}
		return responseBuilder;
	}
	
	
	private ResponseObjectBuilder functionBest(List<Bet> best2, Roulette roulette) {
		ResponseObjectBuilder responseBuilder = request();
		WinnersDto winnersDto = new WinnersDto();
		List<WinnerUsersDto> winnerUsers = new ArrayList<>();
		for (Bet winner : best2) {
			if (winner.getNumber().compareTo(roulette.getWinner()) == 0
					&& winner.getColor().equals(roulette.getColor())) {
				winnersDto = winners(roulette);
				winnerUsers.add(users(winner, ConstantsRoulette.WINNERNUMBER));
			} else if (winner.getColor().equals(roulette.getColor())) {
				winnersDto = winners(roulette);
				winnerUsers.add(users(winner, ConstantsRoulette.WINNERCOLOR));
			} else {
				if (winner.getNumber().compareTo(roulette.getWinner()) == 0) {
					winnersDto = winners(roulette);
					winnerUsers.add(users(winner, ConstantsRoulette.WINNERNUMBER));
				}
			}

		}
		if (!Util.isEmpty(winnerUsers)) {
			rouletteRepository.save(roulette.setStatus(RouletteCodes.INACTIVE.name()));
			winnersDto.setStatus(RouletteCodes.INACTIVE.name());
			responseBuilder.addPayloadProperty(RouletteCodes.WINNERS.name(),
					winnersDto.setCountWinners(winnerUsers.size()).setWinnerUsers(winnerUsers));
		} else
			responseBuilder.addPayloadProperty(RouletteCodes.NOTWINNERS.name(),
					messageSorce(RouletteCodes.NOTWINNERS.name()));

		return responseBuilder;
	}

	private WinnersDto winners(Roulette roulette) {
		return new WinnersDto().setColor(roulette.getColor()).setIdRoulette(roulette.getId().toHexString())
				.setStatus(roulette.getStatus()).setWinnerNumber(roulette.getWinner());
	}

	private WinnerUsersDto users(Bet winner, String typeWinner) {
		return new WinnerUsersDto().setPremium(premium(winner, typeWinner)).setNumber(winner.getNumber())
				.setColor(winner.getColor()).setIdUser(winner.getUser().toHexString());
	}

	private String premium(Bet winner, String typeWinner) {
		if (typeWinner.equals(ConstantsRoulette.WINNERCOLOR)) {
			return String.valueOf(Util.multiplicateResult(winner.getAmount(), 1.8));
		}
		return String.valueOf(Util.multiplicateResult(winner.getAmount(), 5));
	}

	private ResponseObjectBuilder request() {
		ResponseObjectBuilder responseBuilder = rouletteServiceImpl();
		responseBuilder.setRouletteCode(RouletteCodes.REQUEST_PROCESSED_CORRECTLY);
		responseBuilder.setDetailedMessage(RouletteCodes.REQUEST_PROCESSED_CORRECTLY.name());
		return responseBuilder;

	}

	private Optional<Roulette> findRouletteId(String idRoulette) {
		return rouletteRepository.findById(idRoulette);
	}

	public ResponseObjectBuilder rouletteServiceImpl() {
		ResponseObjectBuilder responseBuilder = new ResponseObjectBuilder(messageSource);
		return responseBuilder;
	}

	private String messageSorce(String message) {
		return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
	}

	
	private RouletteDto roulettes(Roulette roulette) {
		return new RouletteDto().setId(roulette.getId().toHexString()).setName(roulette.getName())
				.setStatus(roulette.getStatus());
	}
}
