package org.endava.tempr.service;

import org.endava.tempr.model.UserEntry;
import org.endava.tempr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceBean implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntry> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntry findOne(Long id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public UserEntry createUser(UserEntry user) {
        if(null == user.getId()){
            return null;
        }

        UserEntry savedUser = userRepository.save(user);

        return savedUser;
    }

    @Override
    public UserEntry updateUser(UserEntry user) {
        UserEntry userToUpdate = userRepository.findByUserId(user.getId());

        if(userToUpdate == null){
            return null;
        }

        if(userToUpdate.getUserName() != null){
            userToUpdate.setUserName(user.getUserName());
        }

        if(userToUpdate.getUserPassword() != null){
            userToUpdate.setUserName(user.getUserPassword());
        }

        UserEntry updatedUser = userRepository.save(userToUpdate);

        return updatedUser;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}