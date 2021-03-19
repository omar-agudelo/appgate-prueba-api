package com.appgate.prueba.util;

import java.util.List;
import java.util.UUID;

public class Util {

	public static String getRandomName() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(uuid.lastIndexOf("-") + 1);
	}

	public static Integer dividingResult(Integer accumulated, Integer countUser) {
		return accumulated / countUser;
	}

	public static boolean isEmpty(String string) {
		return string == null || string.isEmpty();
	}

	public static boolean isEmpty(List<?> lista) {
		return lista == null || lista.isEmpty();
	}

	public static Integer multiplicateResult(Integer result, double valor) {
		return Integer.valueOf((int) (result * valor));

	}

}
