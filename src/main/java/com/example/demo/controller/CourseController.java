package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.interfaces.CourseService;
import com.example.demo.service.interfaces.StudentTaskStatusService;
import com.example.demo.service.interfaces.UserService;
import com.example.demo.validator.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseValidator courseValidator;
    @Autowired
    private StudentTaskStatusService studentTaskStatusService;

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        userService.findByUsername(username);
        return userService.findByUsername(username);
    }

    @GetMapping("/courseCreate")
    public String courseAdd(Model model) {
        model.addAttribute("course", new Course());
        return "courseCreate";
    }

    @PostMapping("/courseCreate")
    public String courseAdd(@ModelAttribute("course") Course course, BindingResult bindingResult) {
        courseValidator.validate(course, bindingResult);
        if (bindingResult.hasErrors()) {
            return "courseCreate";
        }

        User user = getCurrentUser();

//        Set<Role> roles = user.getRoles();
//        roles.add(Role.TEACHER);
//        user.setRoles(roles);

        course.setTeacher(user);
        courseService.create(course);
        return "redirect:/start";
    }

    @GetMapping("{course}")
    public String courseShow(@PathVariable Course course, Model model) {
        User user = getCurrentUser();

        Set<Label> labels = new HashSet<>();
        labels.add(Label.NEW);
        labels.add(Label.REOPEN);
        labels.add(Label.DONE);

        Set<StudentTaskStatus> taskStatuses = new HashSet<>();
        for (Task i : course.getTaskSet()) {
            for (StudentTaskStatus j : user.getStudentTaskStatusSet()) {
                if (i.equals(j.getTask())) {
                    taskStatuses.add(j);
                }
            }
        }

        Set<StudentTaskStatus> studentTaskStatuses = new HashSet<>();
        for (Task i : course.getTaskSet()) {
            studentTaskStatuses.add(studentTaskStatusService.findStudentTaskStatusByLabelAndTask(Label.READY_FOR_REVIEW, i));
        }

        if (course.getTeacher().equals(user)) {
            model.addAttribute("course", course);
            model.addAttribute("taskList", course.getTaskSet());
            model.addAttribute("studentList", course.getStudentSet());
            model.addAttribute("taskStatusSet", studentTaskStatuses);
            model.addAttribute("labelList", labels);
            return "teacherCoursePage";
        } else if (course.getStudentSet().contains(user)) {
            model.addAttribute("course", course);
            model.addAttribute("user", user);
            model.addAttribute("taskStatusList", taskStatuses);
            return "studentCoursePage";
        } else {
            model.addAttribute("course", course);
            return "course";
        }
    }

    @GetMapping("/{course}/join")
    public String courseJoin(@PathVariable Course course) {
        User user = getCurrentUser();

        Set<User> students = course.getStudentSet();
        students.add(user);
        course.setStudentSet(students);

        Set<StudentTaskStatus> userTasks = user.getStudentTaskStatusSet();

        for (Task i : course.getTaskSet()) {

            StudentTaskStatus studentTaskStatus = new StudentTaskStatus();
            studentTaskStatus.setTask(i);
            studentTaskStatus.setLabel(Label.NEW);
            studentTaskStatus.setMark(0);

            userTasks.add(studentTaskStatus);
        }

        user.setStudentTaskStatusSet(userTasks);
        for (StudentTaskStatus i : userTasks) {
            i.setStudent(user);
        }

        userService.update(user);

        return "redirect:/course/{course}";
    }
}
