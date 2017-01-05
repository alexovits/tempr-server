package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.model.User;
import edu.endava.tempr.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceBean implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceBean.class);

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        LOG.info("Looking for user with id: '{}'",id);
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        User savedUser = null;
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder(12);
        user.setPassword(pe.encode(user.getPassword()));
        try {
            savedUser = userRepository.save(user);
        } catch(Exception ex){
            LOG.warn("Could not create user: '{}' with exception '{}'", user, ex);
            ex.printStackTrace();
        }
        LOG.info("Created new user with id: '{}'",savedUser.getId());
        return savedUser;
    }

    @Override
    public User updateUser(User user) {
        User userToUpdate = userRepository.findById(user.getId());
        if(userToUpdate == null) {
            LOG.info("User with id: '{}' was not found!", user.getId());
            return null;
        }
        LOG.info("User with id: '{}' was updated!", user.getId());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
        LOG.info("User with id: '{}' was deleted!",id);
    }


}