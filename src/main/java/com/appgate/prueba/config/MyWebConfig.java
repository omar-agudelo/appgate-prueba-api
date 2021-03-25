package com.appgate.prueba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.appgate.prueba.controller.UploadFileController;

@EnableWebMvc
@Configuration
public class MyWebConfig {

  @Bean
  public UploadFileController uploadFileController() {
      return new UploadFileController();
  }

}
