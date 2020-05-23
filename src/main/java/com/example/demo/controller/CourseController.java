package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.service.interfaces.CourseService;
import com.example.demo.service.interfaces.UserService;
import com.example.demo.validator.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseValidator courseValidator;

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

}
