package com.pgm.shopserver.service;

import com.pgm.shopserver.domain.Role;
import com.pgm.shopserver.domain.User;
import com.pgm.shopserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateTime(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void changeRole(Role newRole, String username) {
        userRepository.updateUserRole(username,newRole);
    }

    @Override
    public User findByUsername(String username) {
       User user=userRepository.findByUsername(username);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
