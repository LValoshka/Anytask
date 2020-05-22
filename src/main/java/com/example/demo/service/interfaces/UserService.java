package com.example.demo.service.interfaces;

import com.example.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  {
    void save(User user);

//    User findByLogin(String login);

    User findByUsername(String username);

    User findOne(int id);

    List<User> findAll();

    User create(User entity);

    void update(User entity);

    void delete(User entity);

    void deleteById(int entityId);

    boolean isExistUserById(int id);
}