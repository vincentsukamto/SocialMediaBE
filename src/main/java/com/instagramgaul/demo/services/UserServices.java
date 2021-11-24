package com.instagramgaul.demo.services;

import com.instagramgaul.demo.model.user.User;

import java.util.List;


public interface UserServices {

    public User registerUser(User user);

    public List<User> getAllUsers();

    public User getUserById(int id);

    public User deleteUser(int id);

    public User getUserByEmail(String email);

    public User getUserByUsername(String username);

    public User findActiveUser(String credential);

}