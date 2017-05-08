package edu.endava.tempr.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

public class SpringBootTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
}