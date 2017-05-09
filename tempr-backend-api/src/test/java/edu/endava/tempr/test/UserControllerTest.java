package edu.endava.tempr.test;

import edu.endava.tempr.api.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Created by zsoltszabo on 5/9/17.
 */
public class UserControllerTest extends BaseControllerTest{

    @Autowired
    private UserService userService;

    @Before
    public void setUp(){
        super.setUp();
        System.out.println("Faszom");
        // TODO: Clear cache
    }

    @Test
    public void testUserRegistration() throws Exception {
        final String URI = "/version";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 200", 200, status);
        Assert.assertTrue("Failure - expected HTTP response body to have a value", content.trim().length() > 0);
    }


}
