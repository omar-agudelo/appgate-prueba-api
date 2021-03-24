package com.appgate.prueba.util;

public enum RouletteCodes {
	// Peticiones correctas
	REQUEST_PROCESSED_CORRECTLY(1000),
	REQUEST_NOT_CORRECTLY(1502);
	

	private final int value;

	RouletteCodes(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}
}