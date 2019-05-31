package com.acs.shopify;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: brianfroschauer
 * Date: 2019-05-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreateCustomerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void test001_aValidCustomerWhenCreateShouldReturnCreatedStatus() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "John");
        map.put("lastName", "Doe");
        map.put("username", "johndoe");
        map.put("password", "Password1");

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(map);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void test002_aValidFirstNameWhenCreateCustomerMustHaveBetween2And20Characters() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "J");
        map.put("lastName", "Doe");
        map.put("username", "johndoe");
        map.put("password", "Password1");

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(map);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test003_aValidFirstNameWhenCreateCustomerMustHaveBetween2And20Characters() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "JJJJJJJJJJJJJJJJJJJJJ");
        map.put("lastName", "Doe");
        map.put("username", "johndoe");
        map.put("password", "Password1");

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(map);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test004_aValidLastNameWhenCreateCustomerMustHaveBetween2And20Characters() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "John");
        map.put("lastName", "D");
        map.put("username", "johndoe");
        map.put("password", "Password1");

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(map);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test005_aValidLastNameWhenCreateCustomerMustHaveBetween2And20Characters() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "John");
        map.put("lastName", "DDDDDDDDDDDDDDDDDDDDD");
        map.put("username", "johndoe");
        map.put("password", "Password1");

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(map);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test006_aValidUsernameWhenCreateCustomerMustHaveBetween5And32Characters() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "John");
        map.put("lastName", "Doe");
        map.put("username", "j");
        map.put("password", "Password1");

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(map);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test007_aValidUsernameWhenCreateCustomerShouldNotHaveSpecialCharacters() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "John");
        map.put("lastName", "Doe");
        map.put("username", "johndoe$");
        map.put("password", "Password1");

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(map);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
