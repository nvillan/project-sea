package com.seashells.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.seashells.entity.UserEntity;
import com.seashells.manager.UserManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml", "classpath:test-web.xml"})
@WebAppConfiguration
public class ApplicationControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private UserManager userManagerMock;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(userManagerMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void listUser_FoundAllEntriesAndShowView() throws Exception {
		UserEntity user1 = new UserEntity();
		user1.setAccountNumber(123456);
		user1.setFirstname("Bob");
		user1.setLastname("Miller");
		user1.setEdition("Unlimited");
		user1.setEmail("bob.miller@seapp.com");

		UserEntity user2 = new UserEntity();
		user2.setAccountNumber(78910);
		user2.setFirstname("Ana");
		user2.setLastname("Miller");
		user2.setEdition("Basic");
		user2.setEmail("ana.miller@seapp.com");

		when(userManagerMock.retrieveUsers()).thenReturn(Arrays.asList(user1, user2));

		mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(status().isOk())
				.andExpect(view().name("userList")).andExpect(forwardedUrl("/WEB-INF/view/userList.jsp"))
				.andExpect(model().attribute("userList", hasSize(2)))
				.andExpect(model().attribute("userList",
						hasItem(allOf(hasProperty("accountNumber", is(123456)), hasProperty("firstname", is("Bob"))))))
				.andExpect(model().attribute("userList",
						hasItem(allOf(hasProperty("accountNumber", is(78910)), hasProperty("firstname", is("Ana"))

						))));

		verify(userManagerMock, times(1)).retrieveUsers();
		verifyNoMoreInteractions(userManagerMock);
	}
}