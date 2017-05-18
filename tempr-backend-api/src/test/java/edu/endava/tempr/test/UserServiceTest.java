package edu.endava.tempr.test;

import edu.endava.tempr.api.exception.UserNotFoundException;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.spi.LocaleNameProvider;

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
        Assert.assertEquals("Failure - expected size", 2, userList.size());
    }

    @Test
    public void testFindOne(){
        Long id = 1L;
        Exception exception = null;
        User entity = null;
        try {
            entity = userService.findOne(id);
        } catch (UserNotFoundException e) {
            exception = e;
        }

        Assert.assertNull("Failure - expected no exceptions", exception);
        Assert.assertEquals("Failure - expected id match", entity.getId(), id);
    }

    @Test
    public void testFindOneNotFound(){
        Long id = Long.MAX_VALUE;
        Exception exception = null;
        User entity = null;
        try {
            entity = userService.findOne(id);
        } catch (UserNotFoundException e) {
            exception = e;
        }

        Assert.assertNotNull("Failure - expected exception", exception);
        Assert.assertNull("Failure - expected null", entity);
    }

    @Test
    public void testCreate(){
        User user = new User();
        user.setUsername("Jozsef");
        user.setPassword("Passwordjoska");
        User createdUser = userService.createUser(user);
        Assert.assertNotNull("failure - expected not null", createdUser);
        Assert.assertNotNull("failure - expected id attribute not null", createdUser.getId());
        Assert.assertEquals("failure - expected text attribute match",user.getUsername(), createdUser.getUsername());

        Collection<User> list = userService.findAll();

        Assert.assertEquals("failure - expected size", 3, list.size());
    }
}
