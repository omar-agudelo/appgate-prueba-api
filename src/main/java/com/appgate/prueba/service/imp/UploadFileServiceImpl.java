package com.appgate.prueba.service.imp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appgate.prueba.config.ResponseObject;
import com.appgate.prueba.config.ResponseObjectBuilder;
import com.appgate.prueba.service.UploadFileService;
import com.appgate.prueba.util.RouletteCodes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "rouletteService")
public class UploadFileServiceImpl implements UploadFileService {

	@Autowired
	private MessageSource messageSource;

	@Override
	public ResponseObject uploadFile(MultipartFile file) {
		ResponseObjectBuilder responseBuilder = new ResponseObjectBuilder(messageSource);
		BufferedReader fileReader = null;
		if (file.isEmpty()) {

		} else {
			String line = "";
			String cvsSplitBy = ",";
			// parse CSV file to create a list of `User` objects
			try {
				fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
				while ((line = fileReader.readLine()) != null) {
					String[] datos = line.split(cvsSplitBy);
					// Imprime datos.
					System.out.println(datos[0] + ", " + datos[1] + ", " + datos[2] + ", " + datos[3] + ", " + datos[4]
							+ ", " + datos[5]);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fileReader != null) {
					try {
						fileReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			responseBuilder.setRouletteCode(RouletteCodes.REQUEST_PROCESSED_CORRECTLY);
			responseBuilder.setDetailedMessage(RouletteCodes.REQUEST_PROCESSED_CORRECTLY.name());

		}
		return responseBuilder.build();

	}
}