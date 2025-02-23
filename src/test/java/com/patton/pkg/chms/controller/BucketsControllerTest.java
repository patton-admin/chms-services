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
public class BucketsControllerTest {

	public String accessToken;

	@Autowired
	private MockMvc mockMvc;

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
	public void test_getAllBuckets_without_token() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/allBuckets").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(401));
	}

//	@Autowired
//	TokenGenerationTest testToken;

	@Test
	public void test_getAllBuckets_withToken() throws Exception {
		String accessToken = obtainAccessToken("aravind.guna@gmail.com", "aravind");
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/allBuckets").header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(200));
	}

	@Test
	public void test_Bucket_User_rel() throws Exception {
		String accessToken = obtainAccessToken("aravind.guna@gmail.com", "aravind");
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/getBucketUserRel")
				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));
	}

	@Test
	public void test_getBucketById() throws Exception {
		String accessToken = obtainAccessToken("aravind.guna@gmail.com", "aravind");
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/getBucketById?bucketId=2")
				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));
	}

	@Test
	public void test_getUserByBucketId() throws Exception {
		String accessToken = obtainAccessToken("aravind.guna@gmail.com", "aravind");
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/getUserByBucketId?bucketId=2")
				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));
	}

	@Test
	public void test_getLeadsByBucket() throws Exception {
		String accessToken = obtainAccessToken("aravind.guna@gmail.com", "aravind");
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/getLeadsByBucket?bucketId=2")
				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));
	}

	@Test
	public void getBucketByOwner() throws Exception {
		String accessToken = obtainAccessToken("aravind.guna@gmail.com", "aravind");
		mockMvc.perform(MockMvcRequestBuilders.get("/chms/getBucketByOwner").param("owner", "Mandrin")
				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200)).andDo(MockMvcResultHandlers.print());
	}
}
