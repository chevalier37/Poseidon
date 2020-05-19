package com.poseidon.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

	static String fullToken;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void creatToken() throws Exception {
		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\":\"javainuse\",\"password\":\"password\"}"))
				.andExpect(status().isOk()).andReturn();

		String response = result.getResponse().getContentAsString();
		response = response.replace("{\"access_token\": \"", "");
		String token = response.replace("\"}", "");
		String tokenHash = token.replace("{\"token\":\"", "");
		fullToken = "Bearer " + tokenHash;

	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void postUser() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/user/add").header("Authorization", fullToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"username\":\"usernam12\",\"password\":\"paDdededede?\", \"fullname\":\"fullname\",\"role\":\"role\"}")
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());

	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void getUser() throws Exception {
		this.postUser();
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/user/findId/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void updateUser() throws Exception {
		this.postUser();
		this.mockMvc.perform(MockMvcRequestBuilders.put("/user/update/1").header("Authorization", fullToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"id\":1,\"username\":\"usernam12\",\"password\":\"$2a$10$tLdH7KlDr.VBhqLsnCJcz.yWWQ.Fh6rKMom.4VYSmSOLlSE/Xo/gy\",\"fullname\":\"fullname1\",\"role\":\"role\"}")
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void deleteUser() throws Exception {
		this.postUser();
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/user/delete/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

}
