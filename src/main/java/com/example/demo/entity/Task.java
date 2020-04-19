package com.example.demo.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "task_description")
    private String taskDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StudentTaskStatus studentTaskStatus;

    public Task() {
    }

    public Task(String taskName, String dueDate, String taskDescription, Course course, StudentTaskStatus studentTaskStatus) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.taskDescription = taskDescription;
        this.course = course;
        this.studentTaskStatus = studentTaskStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public StudentTaskStatus getStudentTaskStatus() {
        return studentTaskStatus;
    }

    public void setStudentTaskStatus(StudentTaskStatus studentTaskStatus) {
        this.studentTaskStatus = studentTaskStatus;
    }
}
