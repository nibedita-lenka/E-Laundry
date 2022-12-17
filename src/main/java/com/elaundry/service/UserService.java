package com.elaundry.service;

import com.elaundry.dao.UserDao;
import com.elaundry.entity.User;

import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User getById(Integer id){
        return userDao.findById(id);
    }

    public User createUser(User user){
        return userDao.save(user);
    }

    public List<User> getAll(){
        return userDao.findAll();
    }

    public boolean matchCredentials(String email, String password){
        User user = userDao.findByEmail(email);
        if(user == null){
            return false;
        }
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public User getByEmail(String email){
        return userDao.findByEmail(email);
    }

    public boolean deleteById(Integer id){
        return userDao.deleteById(id);
    }

    public User updateUserById(Integer id, User user){
        user.setId(id);
        return userDao.updateUser(user);
    }

}
