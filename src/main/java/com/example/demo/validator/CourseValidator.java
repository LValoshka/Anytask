package com.example.demo.validator;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CourseValidator implements Validator {
    @Autowired
    private CourseService courseService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Course course = (Course) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseName", "NotEmpty");
        if (course.getCourseName().length() < 3 || course.getCourseName().length() > 100) {
            errors.rejectValue("courseName", "Size.course.courseName");
        }
        if (courseService.findByCourseName(course.getCourseName()) != null) {
            errors.rejectValue("courseName", "Duplicate.course.courseName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseDescription", "NotEmpty");
        if (course.getCourseDescription().length() < 1 || course.getCourseName().length() > 255) {
            errors.rejectValue("courseDescription", "Size.course.description");
        }
    }
}