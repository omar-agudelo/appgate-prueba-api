package com.appgate.prueba.service.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.config.ResponseObjectBuilder;
import com.appgate.prueba.model.File;
import com.appgate.prueba.repository.FileUploadRepository;
import com.appgate.prueba.service.UploadFileService;
import com.appgate.prueba.util.RouletteCodes;
import com.appgate.prueba.util.Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "rouletteService")
public class UploadFileServiceImpl implements UploadFileService {

	@Autowired
	private FileUploadRepository fileUploadRepository;

	@Autowired
	private MessageSource messageSource;

	@Override
	public ResponseObject uploadFile(MultipartFile file) {
		ResponseObjectBuilder responseBuilder = new ResponseObjectBuilder(messageSource);
		BufferedReader fileReader = null;
		try {
			if (file.isEmpty()) {

			} else {
				String line = "";
				String cvsSplitBy = ",";

				fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
				while ((line = fileReader.readLine()) != null) {
					String[] datos = line.split(cvsSplitBy);
					log.info(datos[0] + ", " + datos[1] + ", " + datos[2] + ", " + datos[3] + ", " + datos[4] + ", "
							+ datos[5] + ", " + datos[6] + ", " + datos[7] + ", " + datos[8]);
					fileUploadRepository.save(new File().setIPFrom(Util.replaceComillas(datos[0]))
							.setIPTo(Util.replaceComillas(datos[1])).setCountryCode(Util.replaceComillas(datos[2]))
							.setCountry(Util.replaceComillas(datos[3])).setRegion(Util.replaceComillas(datos[4]))
							.setCity(Util.replaceComillas(datos[5])).setLatitude(Util.replaceComillas(datos[6]))
							.setLongitude(Util.replaceComillas(datos[7])).setTimeZone(Util.replaceComillas(datos[8])));

				}
				responseBuilder.setRouletteCode(RouletteCodes.REQUEST_PROCESSED_CORRECTLY);
				responseBuilder.setDetailedMessage(RouletteCodes.REQUEST_PROCESSED_CORRECTLY.name());

			}

		} catch (Exception e) {
			log.info("Error al cargar el archivo");
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return responseBuilder.build();

	}
}