package com.instagramgaul.demo.services.impl;

import com.instagramgaul.demo.common.exception.GetAllUsersException;
import com.instagramgaul.demo.common.exception.RegisterUserException;
import com.instagramgaul.demo.common.exception.UserNotFoundException;
import com.instagramgaul.demo.model.user.User;
import com.instagramgaul.demo.repository.UserRepository;
import com.instagramgaul.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        try{
            return (List<User>) userRepository.getAllUsers();
        }catch(Exception e){
            throw new GetAllUsersException(e.getMessage());
        }
    }

    @Override
    public User getUserById(int id) {
        try{
            return userRepository.findById(id);
        }catch(Exception e){
            throw new GetAllUsersException(e.getMessage());
        }
    }

    @Override
    public User registerUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RegisterUserException("Registering user encountered error: " + e.getMessage());
        }

        return user;
    }

    @Override
    public User deleteUser(int id) {
        User deletedUser =null;
        try {
            deletedUser = userRepository.findById(id);
            if (deletedUser == null) {
                throw new UserNotFoundException("User not found!");
            } else {
                userRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new UserNotFoundException("User not found!"+ e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new UserNotFoundException("Searching user by email encountered error: " + e.getMessage());
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new UserNotFoundException("Searching user by username encountered error: " + e.getMessage());
        }
    }

    @Override
    public User findActiveUser(String credential) {
        return null;
    }
}
