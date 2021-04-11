package com.demo.cassandra.controller;

import com.demo.cassandra.CassandraApplication;
import com.demo.cassandra.co.CreatePersonCO;
import com.demo.cassandra.co.UpdatePersonCO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CassandraApplication.class)
@FixMethodOrder(MethodSorters.JVM)
public class CassandraController {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void savePerson() throws Exception {
        CreatePersonCO createPersonCO = CreatePersonCO.builder().id(1l).fullName("ABC").age(18).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/person/create").content(asJsonString(createPersonCO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @Order(2)
    public void getPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person/get/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("ABC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value("18"));

    }

    @Test
    @Order(3)
    public void updatePerson() throws Exception {
        UpdatePersonCO updatePersonCO = UpdatePersonCO.builder().id(1l).age(19).fullName("ABC").build();
        mockMvc.perform(MockMvcRequestBuilders.put("/person/update").content(asJsonString(updatePersonCO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("ABC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value("19"));

    }

    @Test
    @Order(4)
    public void deletePerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
