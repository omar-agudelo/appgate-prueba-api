package com.appgate.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@ToString
public class FileDto {

	 private String iPFrom;
	 private String iPTo;
	 private String countryCode; 
	 private String country;
	 private String region;
	 private String city;
	 private String latitude;
	 private String longitude;
	 private String timeZone;

}