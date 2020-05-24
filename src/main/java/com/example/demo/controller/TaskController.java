package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.interfaces.TaskService;
import com.example.demo.validator.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/{course}/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskValidator taskValidator;

    @GetMapping("/taskAdd")
    public String taskAdd(@PathVariable Course course, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("course", course);
        return "taskAdd";
    }

    @PostMapping("/taskAdd")
    public String taskAdd(@PathVariable Course course, @ModelAttribute("task") Task task, BindingResult bindingResult) {
        taskValidator.validate(task, bindingResult);
        if (bindingResult.hasErrors()) {
            return "taskAdd";
        }

        Set<Task> tasks = course.getTaskSet();
        tasks.add(task);
        course.setTaskSet(tasks);
        task.setCourse(course);
        taskService.create(task);


        Set<StudentTaskStatus> statuses;
        StudentTaskStatus studentTaskStatus;
        for (User i : course.getStudentSet()) {
            statuses = i.getStudentTaskStatusSet();

            studentTaskStatus = new StudentTaskStatus();
            studentTaskStatus.setLabel(Label.NEW);
            studentTaskStatus.setMark(0);
            studentTaskStatus.setTask(task);

            statuses.add(studentTaskStatus);
            i.setStudentTaskStatusSet(statuses);
            for (StudentTaskStatus j : i.getStudentTaskStatusSet()) {
                if (j.getTask().getId() == studentTaskStatus.getTask().getId())
                    j.setStudent(i);
            }
        }

        return "redirect:/course/{course}";
    }

}
