package edu.endava.tempr.test;

import edu.endava.tempr.api.exception.UserNotFoundException;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.api.service.impl.UserServiceBean;
import edu.endava.tempr.model.User;
import edu.endava.tempr.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsoltszabo on 5/9/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    private UserRepository userRepository;

    private UserService victimUserService;

    @Before
    public void setUp(){
        victimUserService = new UserServiceBean(userRepository);
    }

    @After
    public void tearDown() {
        // clean up after each test method
    }

    @Test
    public void testFindAll() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<User> returnedUserList = victimUserService.findAll();
        Assert.assertEquals("Failure - expected size match", returnedUserList.size(), userList.size());
    }

    @Test
    public void testFindOneWithValidId() {
        Long userId = 1L;
        Exception exception = null;
        User user = new User();
        user.setId(userId);
        User returnUser = null;
        Mockito.when(userRepository.findById(userId)).thenReturn(user);
        try {
            returnUser = victimUserService.findOne(userId);
        } catch (UserNotFoundException e) {
            exception = e;
        }

        Assert.assertNull(exception);
        Assert.assertEquals(returnUser.getId(), userId);
    }

    @Test
    public void testFindOneWithInvalidId() {
        Exception exception = null;
        Mockito.when(userRepository.findById(1L)).thenReturn(null);
        try {
            victimUserService.findOne(1L);
        } catch (UserNotFoundException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
    }

    @Test
    public void testCreate() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Jozsef");
        user.setPassword("Passwordjoska");

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User createdUser = victimUserService.createUser(user);

        Assert.assertEquals(createdUser.getId(), user.getId());
        Assert.assertEquals(createdUser.getUsername(), user.getUsername());
        Assert.assertEquals(createdUser.getPassword(), user.getPassword());
    }

    //How to test update and delet
}
