package com.appgate.prueba.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "prueba")
@Data
@Accessors(chain = true)
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "IP_from")
	 private String iPFrom;
	@Column(name = "IP_to")
	 private String iPTo;
	@Column(name = "Country_code")
	 private String countryCode;
	@Column(name = "Country")
	 private String country;
	@Column(name = "Region")
	 private String region;
	@Column(name = "City")
	 private String city;
	@Column(name = "Latitude")
	 private String latitude;
	@Column(name = "Longitude")
	 private String longitude;
	@Column(name = "TimeZone")
	 private String timeZone;
	
}