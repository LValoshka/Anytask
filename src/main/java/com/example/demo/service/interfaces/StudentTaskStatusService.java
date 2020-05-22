package com.example.demo.service.interfaces;

import com.example.demo.entity.Label;
import com.example.demo.entity.StudentTaskStatus;
import com.example.demo.entity.Task;

public interface StudentTaskStatusService  {

    StudentTaskStatus findOne(int id);

    StudentTaskStatus findStudentTaskStatusByLabelAndTask(Label label, Task task);

    StudentTaskStatus create(StudentTaskStatus entity);

    StudentTaskStatus save(StudentTaskStatus task);

    void update(StudentTaskStatus entity);

    void delete(StudentTaskStatus entity);

    void deleteById(int entityId);
}
