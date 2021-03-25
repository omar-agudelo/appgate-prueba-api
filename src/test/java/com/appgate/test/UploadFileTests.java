package com.appgate.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UploadFileTests {

	private static final String PATH_FILEE = "prueba.csv";

	@Autowired
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Test
	public void uploadFileTest() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "prueba.txt", MediaType.TEXT_PLAIN_VALUE,
				"Hola!".getBytes());

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mvc.perform(multipart("/v1/upload").file(file)).andExpect(status().isOk());
	}

}
