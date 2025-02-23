package com.patton.pkg.chms.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
public class LeadControllerTest {

	/**
	 * ref : https://spring.io/guides/gs/testing-web/
	 * https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
	 * 
	 * @AutoConfigureMockMvc without this you will get nullpointer error while
	 *                       testing the application...
	 */

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_getAllLeads_without_token() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/allLeads").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(401));
	}

	public String obtainAccessToken(String username, String password) throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("client_id", "fooClientIdPassword");
		params.add("username", username);
		params.add("password", password);

		String usernamePassword = "{\"username\":\"aravind.guna91@gmail.com\",\"password\":\"aravind\"}";

		ResultActions result = mockMvc.perform(post("/chms/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(usernamePassword).accept("application/json")).andExpect(status().isOk());

		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("jwtToken").toString();
	}

	@Test
	public void test_getAllLeads_withToken() throws Exception {
		String accessToken = obtainAccessToken("aravind.guna@gmail.com", "aravind");
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/allLeads").header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(200));
	}

	@Test
	public void test_getLeads_ByLeadId_withToken() throws Exception {
		String accessToken = obtainAccessToken("aravind.guna@gmail.com", "aravind");
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/getLead?id=20")
				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));
	}

	// to print the response from the mockMvc...
	@Test
	public void test_allLeadSubsetExampleUsingInterface() throws Exception {
		String accessToken = obtainAccessToken("aravind.guna@gmail.com", "aravind");
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/allLeadSubsetExampleUsingInterface?id=20")
				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200)).andDo(MockMvcResultHandlers.print());
	}

}
