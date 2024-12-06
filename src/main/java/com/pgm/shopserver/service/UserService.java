package com.pgm.shopserver.service;

import com.pgm.shopserver.domain.Role;
import com.pgm.shopserver.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User findByUsername(String username);

    void changeRole(Role newRole, String username);

    List<User> findAllUsers();
}
