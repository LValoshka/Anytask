package com.example.demo.repository;

import com.example.demo.entity.Label;
import com.example.demo.entity.StudentTaskStatus;
import com.example.demo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StudentTaskStatusRepository extends JpaRepository<StudentTaskStatus, Integer> {
    StudentTaskStatus findStudentTaskStatusByLabelAndTask(Label label, Task task);
}
