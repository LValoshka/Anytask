package com.example.demo.service.impls;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course findByCourseName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    @Override
    public Course findOne(int id) {
        return courseRepository.getOne(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course create(Course entity) {
        Course course = courseRepository.saveAndFlush(entity);
        return course;
    }

    @Override
    public Course save(Course course) {
        return courseRepository.saveAndFlush(course);
    }


    @Override
    public void update(Course entity) {
        courseRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Course entity) {
        courseRepository.delete(entity);
    }

    @Override
    public void deleteById(int entityId) {
        courseRepository.deleteById(entityId);
    }

    @Override
    public boolean isExistUserById(int id) {
        return courseRepository.existsById(id);
    }
}
