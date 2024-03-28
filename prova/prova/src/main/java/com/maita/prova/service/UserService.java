package com.maita.prova.service;

import com.maita.prova.model.User;

import java.util.List;

public interface UserService {

    public User findUserById(Long userId) throws Exception;

    public User findUserByJwt(String jwt) throws Exception;

    public void deleteUser(User user, String jwt) throws Exception;

    public boolean verificationUser(User user, String jwt) throws Exception;

    public List<User> findAllUser();
}
