package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean save(User user) {
     //   User userFromDB = userRepository.findByLogin(user.getName());

    //    if(userFromDB != null) return false;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findOne(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User entity) {
        User user = userRepository.saveAndFlush(entity);
        return user;
    }

    @Override
    public void update(User entity) {
        userRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(User entity) {
        if (userRepository.existsById(entity.getId())) {
            userRepository.delete(entity);
        }
    }

    @Override
    public void deleteById(int entityId) {
        userRepository.deleteById(entityId);
    }

    @Override
    public boolean isExistUserById(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(s);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }
}