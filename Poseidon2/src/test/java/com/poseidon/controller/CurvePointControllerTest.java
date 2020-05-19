package com.poseidon.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
public class CurvePointControllerTest {

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
	public void postCurvePoint() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/add").header("Authorization", fullToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"id\":1,\"curveId\":10,\"asOfDate\":null,\"term\":10.0,\"value\":10.0,\"creationDate\":null}")
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(content().string(
						"{\"id\":1,\"curveId\":10,\"asOfDate\":null,\"term\":10.0,\"value\":10.0,\"creationDate\":null}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void getCurvePoint() throws Exception {
		this.postCurvePoint();
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/curvePoint/findId/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(content().string(
						"{\"id\":1,\"curveId\":10,\"asOfDate\":null,\"term\":10.0,\"value\":10.0,\"creationDate\":null}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void updateCurvePoint() throws Exception {
		this.postCurvePoint();
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/curvePoint/update/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"curveId\":10,\"term\":10.0,\"value\":100.0}").accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(content().string(
						"{\"id\":1,\"curveId\":10,\"asOfDate\":null,\"term\":10.0,\"value\":100.0,\"creationDate\":null}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void deleteCurvePoint() throws Exception {
		this.postCurvePoint();
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/curvePoint/delete/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

}
