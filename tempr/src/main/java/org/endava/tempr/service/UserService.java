package org.endava.tempr.service;

import org.endava.tempr.model.UserEntry;

import java.util.List;

/**
 * Created by szasanyi on 11/28/16.
 */
public interface UserService {

    List<UserEntry> findAll();

    UserEntry findOne(Long id);

    UserEntry createUser(UserEntry user);

    UserEntry updateUser(UserEntry user);

    void deleteUser(Long id);
}
