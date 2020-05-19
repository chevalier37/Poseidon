package com.poseidon.integration;

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
public class TradeTestController {

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
	public void postTrade() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/trade/add").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON).content("{\"account\":\"account\",\"type\":\"type\"}")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(content().string(
						"{\"tradeId\":1,\"account\":\"account\",\"type\":\"type\",\"buyQuantity\":null,\"sellQuantity\":null,\"buyPrice\":null,\"sellPrice\":null,\"benchmark\":null,\"tradeDate\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void getRuleName() throws Exception {
		this.postTrade();
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/trade/findId/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(content().string(
						"{\"tradeId\":1,\"account\":\"account\",\"type\":\"type\",\"buyQuantity\":null,\"sellQuantity\":null,\"buyPrice\":null,\"sellPrice\":null,\"benchmark\":null,\"tradeDate\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void updateRuleName() throws Exception {
		this.postTrade();
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/trade/update/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON).content("{\"account\":\"account\",\"type\":\"type1\"}")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(content().string(
						"{\"tradeId\":1,\"account\":\"account\",\"type\":\"type1\",\"buyQuantity\":null,\"sellQuantity\":null,\"buyPrice\":null,\"sellPrice\":null,\"benchmark\":null,\"tradeDate\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void deleteRuleName() throws Exception {
		this.postTrade();
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/trade/delete/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

}
