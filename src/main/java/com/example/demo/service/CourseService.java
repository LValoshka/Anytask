package com.example.demo.service;
import com.example.demo.entity.Course;

import java.util.List;

public interface CourseService {

    Course findByCourseName(String courseName);

    Course findOne(int id);

    List<Course> findAll();

    Course create(Course entity);

    Course save(Course course);

    void update(Course entity);

    void delete(Course entity);

    void deleteById(int entityId);

    boolean isExistUserById(int id);
}
