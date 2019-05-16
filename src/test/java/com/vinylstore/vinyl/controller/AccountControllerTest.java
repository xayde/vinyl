package com.vinylstore.vinyl.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createAccountTest() throws Exception {
        String uri = "/api/users";
        String postBody = "{\n" +
                "\"firstname\":\"firstname\",\n" +
                "\"lastname\":\"lastname\",\n" +
                "\"password\":\"password\",\n" +
                "\"email\":\"email1@gmeail.com\"\n" +
                "}";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(postBody)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(201, status);
        assertEquals("Account has been successfully created", content);
    }
}
