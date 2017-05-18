package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.exception.UserNotFoundException;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.model.User;
import edu.endava.tempr.model.UserType;
import edu.endava.tempr.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceBean implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceBean.class);

    private UserRepository userRepository;

    @Autowired
    public UserServiceBean(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    } // God mode ¯\_(ツ)_/¯

    @Override
    public User findOne(Long id) throws UserNotFoundException {
        LOG.info("Looking for user with id: '{}'",id);
        User user = userRepository.findById(id);
        if(user == null) throw new UserNotFoundException(String.format("No user found with id %1$d", id));
        return user;
    }

    @Override
    public User findByName(String userName) throws UserNotFoundException {
        User user = userRepository.findByUsername(userName);
        if(user == null) throw new UserNotFoundException(String.format("No user found with username %1$s",userName));
        return user;
    }

    @Override
    public User createUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserType(UserType.USER);
        User savedUser = userRepository.save(user);
        LOG.info("Created new user with id: '{}'", savedUser.getId());
        return savedUser;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        User userToUpdate = findOne(user.getId()); // In order to check if the user with the given ID exists at all
        LOG.info("User with id: '{}' was updated!", user.getId());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
        LOG.info("User with id: '{}' was deleted!",id);
    }
}