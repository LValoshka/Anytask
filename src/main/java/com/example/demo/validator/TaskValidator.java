package com.example.demo.validator;


import com.example.demo.entity.Task;
import com.example.demo.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

@Component
public class TaskValidator implements Validator {

    @Autowired
    private TaskService taskService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Task.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Task task = (Task) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskName", "NotEmpty");
        if (task.getTaskName().length() < 3 || task.getTaskName().length() > 100) {
            errors.rejectValue("taskName", "Size.task.name");
            if (taskService.findByTaskName(task.getTaskName()) != null) {
                errors.rejectValue("taskName", "Duplicate.task.name");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskDescription", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dueDate", "NotEmpty");
    }
}
