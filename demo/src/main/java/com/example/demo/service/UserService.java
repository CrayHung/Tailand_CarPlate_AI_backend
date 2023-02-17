package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Users;

public interface UserService {
    

    public List<Users> getAllUsers();
    public Users getUserByID(int ID);
    public Users addOrUpdateUser(Users user);
    public Users deleteUser(int ID) throws Exception;
}

