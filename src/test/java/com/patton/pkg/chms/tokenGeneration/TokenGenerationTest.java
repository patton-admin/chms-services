package com.patton.pkg.chms.tokenGeneration;

//@Component
public class TokenGenerationTest {

//	@Autowired
//	private MockMvc mockMvc;

//	public String obtainAccessToken(String username, String password) throws Exception {
//
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type", "password");
//		params.add("client_id", "fooClientIdPassword");
//		params.add("username", username);
//		params.add("password", password);
//
//		String usernamePassword = "{\"username\":\"aravind.guna91@gmail.com\",\"password\":\"aravind\"}";
//
//		ResultActions result = mockMvc.perform(post("/chms/authenticate").contentType(MediaType.APPLICATION_JSON)
//				.content(usernamePassword).accept("application/json")).andExpect(status().isOk());
//
//		String resultString = result.andReturn().getResponse().getContentAsString();
//
//		JacksonJsonParser jsonParser = new JacksonJsonParser();
//		return jsonParser.parseMap(resultString).get("jwtToken").toString();
//	}
}
