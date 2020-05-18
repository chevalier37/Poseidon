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
public class BidTestController {

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
	public void postBidList() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/bidList/add").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":5}")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(content().string(
						"{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":5.0,\"askQuantity\":null,\"bid\":null,\"ask\":null,\"benchmark\":null,\"bidListDate\":null,\"commentary\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null,\"bidListId\":1}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void getBidList() throws Exception {
		this.postBidList();
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/bidList/findId/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":5}")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(content().string(
						"{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":5.0,\"askQuantity\":null,\"bid\":null,\"ask\":null,\"benchmark\":null,\"bidListDate\":null,\"commentary\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null,\"bidListId\":1}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void updateBidList() throws Exception {
		this.postBidList();
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/bidList/update/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":50}")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(content().string(
						"{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":50.0,\"askQuantity\":null,\"bid\":null,\"ask\":null,\"benchmark\":null,\"bidListDate\":null,\"commentary\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null,\"bidListId\":1}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void deleteBidList() throws Exception {
		this.postBidList();
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/bidList/delete/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":50}")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

}
