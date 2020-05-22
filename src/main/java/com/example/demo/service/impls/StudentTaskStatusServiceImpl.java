package com.example.demo.service.impls;

import com.example.demo.entity.Label;
import com.example.demo.entity.StudentTaskStatus;
import com.example.demo.entity.Task;
import com.example.demo.repository.StudentTaskStatusRepository;
import com.example.demo.service.interfaces.StudentTaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentTaskStatusServiceImpl implements StudentTaskStatusService {

    @Autowired
    private StudentTaskStatusRepository studentTaskStatusRepository;

    @Override
    public StudentTaskStatus findOne(int id) {
        return studentTaskStatusRepository.getOne(id);
    }

    @Override
    public StudentTaskStatus findStudentTaskStatusByLabelAndTask(Label label, Task task) {
        return studentTaskStatusRepository.findStudentTaskStatusByLabelAndTask(label, task);
    }

    @Override
    public StudentTaskStatus create(StudentTaskStatus entity) {
        return studentTaskStatusRepository.saveAndFlush(entity);
    }

    @Override
    public StudentTaskStatus save(StudentTaskStatus task) {
        return studentTaskStatusRepository.saveAndFlush(task);
    }

    @Override
    public void update(StudentTaskStatus entity) {
        studentTaskStatusRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(StudentTaskStatus entity) {
        studentTaskStatusRepository.delete(entity);
    }

    @Override
    public void deleteById(int entityId) {
        studentTaskStatusRepository.deleteById(entityId);
    }
}
