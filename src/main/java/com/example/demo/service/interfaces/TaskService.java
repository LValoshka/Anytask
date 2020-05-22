package com.example.demo.service.interfaces;


import com.example.demo.entity.Task;

import java.util.List;

public interface TaskService {

    Task findByTaskName(String name);

    Task findOne(int id);

    List<Task> findAll();

    Task create(Task entity);

    Task save(Task task);

    void update(Task entity);

    void delete(Task entity);

    void deleteById(int entityId);
}
