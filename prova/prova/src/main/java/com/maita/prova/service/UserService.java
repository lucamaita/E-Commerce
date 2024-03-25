package com.maita.prova.service;

import com.maita.prova.model.User;

public interface UserService {

    public User findUserById(Long userId) throws Exception;

}
