package edu.endava.tempr.api.service;


import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;

import java.util.List;

/**
 * Created by szasanyi on 11/28/16.
 */
public interface UserService {

    List<User> findAll();

    User findOne(Long id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);
}
