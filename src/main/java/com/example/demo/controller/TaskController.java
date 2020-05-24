package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.interfaces.CloudinaryService;
import com.example.demo.service.interfaces.TaskService;
import com.example.demo.service.interfaces.UserService;
import com.example.demo.validator.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/{course}/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskValidator taskValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private CloudinaryService cloudinaryService;

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

    @GetMapping("/{task}/delete")
    public String deleteTask(@PathVariable Task task, @PathVariable Course course) {
        taskService.delete(task);
        return "redirect:/course/{course}";
    }

    @GetMapping("{task}/edit")
    public String taskEdit(@PathVariable Task task, Model model) {
        model.addAttribute("task", task);
        return "taskEdit";
    }

    @PostMapping("{task}/edit")
    public String taskEdit(@PathVariable Course course, @PathVariable Task task, @ModelAttribute("task") Task modelTask, BindingResult bindingResult) {
        taskValidator.validate(task, bindingResult);
        if (bindingResult.hasErrors()) {
            return "taskEdit";
        }

        task.setTaskName(task.getTaskName());
        task.setCourse(course);
        taskService.update(task);

        return "redirect:/course/{course}";
    }

    @GetMapping("{task}/setStartDate")
    public String setStartDate(@PathVariable Task task) {
        String currentDate = getCurrentDate();
        task.getStudentTaskStatus().setStartDate(currentDate);
        task.getStudentTaskStatus().setLabel(Label.IN_PROGRESS);
        taskService.update(task);
        return "redirect:/course/{course}";
    }

    //or edit with uploading
    @GetMapping("{task}/setEndDate")
    public String setEndDate(@PathVariable Task task) {
        String currentDate = getCurrentDate();
        task.getStudentTaskStatus().setEndDate(currentDate);
        task.getStudentTaskStatus().setLabel(Label.READY_FOR_REVIEW);
        taskService.update(task);
        return "redirect:/course/{course}";
    }

    @GetMapping("/{task}/upload")
    public String uploadForm(@PathVariable Task task) {
        return "studentCoursePage";
    }

    @PostMapping("/{task}/upload")
    public String uploadFile(@PathVariable Task task, @RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "studentCoursePage";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        String url = cloudinaryService.uploadFile(file);

        StudentTaskStatus studentTaskStatus = new StudentTaskStatus();
        studentTaskStatus.setTask(task);

        for (StudentTaskStatus i : user.getStudentTaskStatusSet()) {
            if (i.getTask().getId() == studentTaskStatus.getTask().getId()) {
                i.setUrl(url);
                i.setLabel(Label.READY_FOR_REVIEW);
                i.setEndDate(getCurrentDate());
            }
        }

        taskService.save(task);
        return "redirect:/course/{course}";
    }

    @GetMapping("{task}")
    public String taskShow(@PathVariable Task task, Model model) {
        model.addAttribute("task", task);
        model.addAttribute("studentList", task.getCourse().getStudentSet());
        return "teacherTaskShow";
    }

    @GetMapping("/{task}/setReopen")
    public String setLabelReopenForTask(@PathVariable Task task, @PathVariable Course course) {
        task.getStudentTaskStatus().setLabel(Label.REOPEN);
        task.getStudentTaskStatus().setMark(0);
        taskService.update(task);
        return "redirect:/{course}/task/{task}";
    }

    @GetMapping("/tasksInProgress")
    public String getInProgressTasks(@PathVariable Course course, Model model){
        Set<StudentTaskStatus> studentTaskStatusSet = (Set<StudentTaskStatus>) course.getTaskSet().stream().map(Task::getStudentTaskStatus);
        Set<StudentTaskStatus> inProgressTasks = studentTaskStatusSet.stream().filter( x->x.getLabel().equals(Label.IN_PROGRESS)).collect(Collectors.toSet());
        model.addAttribute("inProgressTasks", inProgressTasks);
        return "redirect:/course/{course}";
    }
    private String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return now.format(dtf);
    }
}
