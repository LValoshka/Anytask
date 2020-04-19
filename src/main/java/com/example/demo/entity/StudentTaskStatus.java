package com.example.demo.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "student_task_status")
public class StudentTaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User student;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Task task;

    @Column(name = "label")
    @Enumerated(EnumType.STRING)
    private Label label;

    @Column(name = "mark")
    private int mark;

    @Column(name = "url")
    private String url;

    public StudentTaskStatus() {
    }

    public StudentTaskStatus(User student, Task task, Label label, int mark, String url) {
        this.student = student;
        this.task = task;
        this.label = label;
        this.mark = mark;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
