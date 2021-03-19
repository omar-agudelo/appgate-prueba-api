package com.appgate.prueba.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.appgate.prueba.util.RouletteCodes;

public class ResponseObjectBuilder {

	private MessageSource messageSource;
	private Map<String, Object> payload;
	private RouletteCodes rouletteCode;
	private String detailedMessage;

	
	public ResponseObjectBuilder(MessageSource messageSource) {
		this.payload = new HashMap<>();
		this.messageSource = messageSource;
	}

	public void addPayloadProperty(String field, Object value) {
		payload.put(field, value);
		
	}

	public void setRouletteCode(RouletteCodes rouletteCode) {
		this.rouletteCode = rouletteCode;
	}

	public void setDetailedMessage(String detailedMessage) {
		this.detailedMessage = detailedMessage;
	}
	
	
	public ResponseObject build() {
		ResponseObject responseObject = new ResponseObject();
		responseObject.setDetailedMessage(detailedMessage);
		responseObject.setPayload(payload);
		responseObject.setMessage(messageSource.getMessage(rouletteCode.name(), null, LocaleContextHolder.getLocale()));
		responseObject.setCode(rouletteCode.value());
		return responseObject;
	}

	@Override
	public String toString() {
		return "ResponseObjectBuilder [messageSource=" + messageSource + ", payload=" + payload + ", RouletteCode="
				+ rouletteCode + ", detailedMessage=" + detailedMessage + "]";
	}
	
	
	

}
