package com.example.demo.validator;

import com.example.demo.entity.StudentTaskStatus;
import com.example.demo.service.interfaces.StudentTaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StudentTaskStatusValidator implements Validator {
    @Autowired
    private StudentTaskStatusService studentTaskStatusService;

    @Override
    public boolean supports(Class<?> aClass) {
        return StudentTaskStatus.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StudentTaskStatus task = (StudentTaskStatus) o;
        if (task.getMark() < 0) {
            errors.rejectValue("mark", "Negative.task.mark");
        }
        if (task.getMark() > 10) {
            errors.rejectValue("mark", "Invalid.task.mark");
        }

//        Pattern pattern = Pattern.compile("[0-10]\\d*");
//        Matcher matchDates = pattern.matcher(Integer.toString(task.getMark()));
//        if (!matchDates.matches()) {
//            errors.rejectValue("mark", "Invalid.task.naturalMark");
//        }
    }
}

