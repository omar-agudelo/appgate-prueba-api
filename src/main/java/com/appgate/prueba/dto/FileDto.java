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

	 private String countryCode; 
	 private String region;
	 private String city;
	 private String timeZone;

}