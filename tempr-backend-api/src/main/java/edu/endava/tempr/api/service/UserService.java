package edu.endava.tempr.api.service;


import edu.endava.tempr.api.exception.UserNotFoundException;
import edu.endava.tempr.model.User;

import java.util.List;

/**
 * Created by szasanyi on 11/28/16.
 */
public interface UserService {

    List<User> findAll();

    User findOne(Long id) throws UserNotFoundException;

    User createUser(User user);

    User updateUser(User user) throws UserNotFoundException;

    void deleteUser(Long id);

    User findByName(String userName) throws UserNotFoundException;
}
