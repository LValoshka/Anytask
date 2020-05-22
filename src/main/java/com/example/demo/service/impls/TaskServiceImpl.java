package com.example.demo.service.impls;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task findByTaskName(String name) {
        return null;
    }

    @Override
    public Task findOne(int id) {
        return taskRepository.getOne(id);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task create(Task entity) {
        Task task = taskRepository.saveAndFlush(entity);
        return task;
    }

    @Override
    public Task save(Task task) {
        Task editedTask = taskRepository.saveAndFlush(task);
        return editedTask;
    }

    @Override
    public void update(Task entity) {
        taskRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Task entity) {
        taskRepository.delete(entity);
    }

    @Override
    public void deleteById(int entityId) {
        taskRepository.deleteById(entityId);
    }
}
