package org.jab.microservices.controller;

import org.jab.microservices.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.Assert.assertNotNull;

public class ControllerTest extends AbstractIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void simpleTest() {
        String fooResource = "/accounts/1";

        String result = restTemplate.getForObject(fooResource, String.class);

        System.out.println(result);
        assertNotNull(result);
        //assertEquals("value is set", "bar", result);
    }

}