package com.example.demo.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService  {
    boolean save(User user);

    User findByLogin (String login);

    User findOne(int id);

    List<User> findAll();

    User create(User entity);

    void update(User entity);

    void delete(User entity);

    void deleteById(int entityId);

    boolean isExistUserById(int id);
}