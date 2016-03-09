package com.opengroupe.cloud.saas.web;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.opengroupe.cloud.saas.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ViewControllerTest {
	private MockMvc mvc;
	
	@Resource
	WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getDefaultGreetings() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/greeting"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"))
			.andExpect(model().attribute("name", equalTo("World")))
			.andExpect(view().name("greeting"));
	}
}
