package com.example.demo.service.interfaces;

import com.example.demo.entity.Label;
import com.example.demo.entity.StudentTaskStatus;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;

import java.util.List;

public interface StudentTaskStatusService  {

    StudentTaskStatus findOne(int id);

    StudentTaskStatus findStudentTaskStatusByLabelAndTask(Label label, Task task);

    List<StudentTaskStatus> findAllByLabelAndTaskAndStudent(Label label, Task task, User user);

    StudentTaskStatus create(StudentTaskStatus entity);

    StudentTaskStatus save(StudentTaskStatus task);

    void update(StudentTaskStatus entity);

    void delete(StudentTaskStatus entity);

    void deleteById(int entityId);
}
