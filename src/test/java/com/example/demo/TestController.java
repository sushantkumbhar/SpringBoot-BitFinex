package com.example.demo;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class TestController extends Demo5ApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetTicker() throws Exception {
		mockMvc.perform(get("/api/v1/ticker"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[0].bid").value(1))
		.andExpect(jsonPath("$[0].bidSize").value(1)).andExpect(jsonPath("$[0].ask").value(1))
		.andExpect(jsonPath("$[0].askSize").value(1)).andExpect(jsonPath("$[0].dailyChange").value(1))
		.andExpect(jsonPath("$[0].dailyChangeRelative").value(1)).andExpect(jsonPath("$[0].lastPrice").value(1))
		.andExpect(jsonPath("$[0].volume").value(1)).andExpect(jsonPath("$[0].high").value(1))
		.andExpect(jsonPath("$[0].low").value(1));

	}

}