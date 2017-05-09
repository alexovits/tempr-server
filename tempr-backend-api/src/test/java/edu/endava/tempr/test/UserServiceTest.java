package edu.endava.tempr.test;

import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by zsoltszabo on 5/9/17.
 */
@Transactional
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp(){
        // clean up before each test method
    }

    @After
    public void tearDown(){
        // clean up after each test method
    }

    @Test
    public void testFindAll(){
        Collection<User> userList = userService.findAll();

        Assert.assertNotEquals("Failure - expected not null", userList);
        Assert.assertEquals("failure - expected size", 2, userList.size());
    }
}
