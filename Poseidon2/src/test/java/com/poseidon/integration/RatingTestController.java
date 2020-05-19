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
public class RatingTestController {

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
	public void postRating() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/rating/add").header("Authorization", fullToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"moodysRating\":\"moodysRating\",\"sandPRating\":\"sandPRating\",\"fitchRating\":\"fitchRating\", \"orderNumber\":10}")
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(content().string(
						"{\"id\":1,\"moodysRating\":\"moodysRating\",\"sandPRating\":\"sandPRating\",\"fitchRating\":\"fitchRating\",\"orderNumber\":10}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void getRating() throws Exception {
		this.postRating();
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/rating/findId/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(content().string(
						"{\"id\":1,\"moodysRating\":\"moodysRating\",\"sandPRating\":\"sandPRating\",\"fitchRating\":\"fitchRating\",\"orderNumber\":10}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void updateRating() throws Exception {
		this.postRating();
		this.mockMvc.perform(MockMvcRequestBuilders.put("/rating/update/1").header("Authorization", fullToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"moodysRating\":\"moodysRating\",\"sandPRating\":\"sandPRating\",\"fitchRating\":\"fitchRating\", \"orderNumber\":100}")
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(content().string(
						"{\"id\":1,\"moodysRating\":\"moodysRating\",\"sandPRating\":\"sandPRating\",\"fitchRating\":\"fitchRating\",\"orderNumber\":100}"));
	}

	@Test
	@Sql({ "/poseidonTest.sql" })
	public void deleteRating() throws Exception {
		this.postRating();
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/rating/delete/1").header("Authorization", fullToken)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

}
