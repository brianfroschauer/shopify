package com.acs.shopify;

import com.acs.shopify.dto.customer.ResponseCustomer;
import com.acs.shopify.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;


/**
 * Author: brianfroschauer
 * Date: 2019-05-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@TestPropertySource("/application.properties")
public class CustomerControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    private ResponseCustomer customer;

    @Before
    public void givenRegisteredCustomer() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "John");
        map.put("lastName", "Doe");
        map.put("username", "johndoe");
        map.put("password", "Password1");

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(map);

        final ResultActions resultActions = mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        final String responseBody = resultActions
                .andReturn()
                .getResponse()
                .getContentAsString();

         customer = mapper.readValue(responseBody, ResponseCustomer.class);
    }

    @Test
    public void test001_whenFindOneThenReturnOkStatus() throws Exception {

        mockMvc.perform(get("/customers/{id}", customer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test002_whenFindAllThenReturnOkStatus() throws Exception {

        mockMvc.perform(get("/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test003_whenCreateThenReturnCreatedStatus() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "Jane");
        map.put("lastName", "Doe");
        map.put("username", "janedoe");
        map.put("password", "Password1");

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(map);

        final ResultActions resultActions = mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());

        final String responseBody = resultActions
                .andReturn()
                .getResponse()
                .getContentAsString();

        final ResponseCustomer mapped = new ObjectMapper().readValue(responseBody, ResponseCustomer.class);

        mockMvc.perform(delete("/customers/{id}", mapped.getId())
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void test004_whenUpdateThenReturnOkStatus() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "Jane");
        map.put("lastName", "Doe");
        map.put("username", "janedoe");
        map.put("password", "Password1");

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(map);

        final ResultActions resultActions = mockMvc.perform(put("/customers/{id}", customer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());

        final String responseBody = resultActions
                .andReturn()
                .getResponse()
                .getContentAsString();

        final ResponseCustomer mapped = new ObjectMapper().readValue(responseBody, ResponseCustomer.class);

        assertEquals("Jane", mapped.getFirstName());
    }

    @Test
    public void test005_whenDeleteThenReturnNoContentStatus() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "Jane");
        map.put("lastName", "Doe");
        map.put("username", "janedoe");
        map.put("password", "Password1");

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(map);

        final ResultActions resultActions = mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        final String responseBody = resultActions
                .andReturn()
                .getResponse()
                .getContentAsString();

        ResponseCustomer mapped = new ObjectMapper().readValue(responseBody, ResponseCustomer.class);

        mockMvc.perform(delete("/customers/{id}", mapped.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @After
    public void tearDown() {

        customerRepository.deleteAll();
    }
}
