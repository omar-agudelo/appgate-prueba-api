package com.appgate.prueba.config;

import java.util.Map;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class ResponseObject {

	private String detailedMessage;
	private String message;
	private int code;
	private Map<String, Object> payload;

}