package org.jab.microservices.controller;

import org.jab.microservices.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ControllerTest2 extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void simpleTest() throws Exception {
        String fooResource = "/accounts/1";

        this.mockMvc.perform(get(fooResource)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


        this.mockMvc.perform(get(fooResource)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}