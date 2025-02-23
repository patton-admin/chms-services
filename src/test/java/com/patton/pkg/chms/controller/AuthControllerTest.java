package com.patton.pkg.chms.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patton.pkg.chms.auth.JwtRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_getHelp() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/help").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(401));
	}

	@Test
	public void test_token_generation() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/chms/authenticate")
				.content(asJsonString(new JwtRequest("aravind.guna91@gmail.com", "aravind")))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/***
	 * For converting the string to JSON...
	 * 
	 * @param obj
	 * @return
	 */
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
