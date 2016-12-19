package edu.endava.tempr.api.service;

import edu.endava.tempr.api.util.EncryptionProvider;
import edu.endava.tempr.model.User;
import edu.endava.tempr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceBean implements UserService {

    @Autowired
    private UserRepository userRepository;

    private EncryptionProvider encryptionProvider;

    public UserServiceBean(){
        encryptionProvider = new EncryptionProvider();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
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
            //Log it later
            ex.printStackTrace();
        }
        return savedUser;
    }

    @Override
    public User updateUser(User user) {
        User userToUpdate = userRepository.findById(user.getId());
        if(userToUpdate == null) {
            return null;
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }


}