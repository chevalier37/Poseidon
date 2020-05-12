package com.poseidon.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.poseidon.controllers.BidListController;


@WebMvcTest(BidListController.class)
@ExtendWith(SpringExtension.class)
public class BidTestController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void postBidList() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/bidList/add").contentType(MediaType.APPLICATION_JSON)
				.content("{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":5}").accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(content().string(
						"{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":5.0,\"askQuantity\":null,\"bid\":null,\"ask\":null,\"benchmark\":null,\"bidListDate\":null,\"commentary\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null,\"bidListId\":1}"));
	}

}
