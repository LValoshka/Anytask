package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Size(min=1, message = "Поле не может быть пустым.")
    @Column(name = "user_name")
    private String name;

    @Size(min=1, message = "Поле не может быть пустым.")
    @Column(name = "user_surname")
    private String surname;

    @Size(min=5, message = "Не меньше 5 знаков.")
    @Column(name = "user_login")
    private String login;

    @Size(min=5, message = "Не меньше 5 знаков.")
    @Column(name = "password")
    private String password;

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Transient //what does it mean?
    private String passwordConfirm;

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //возможно, это всё сломает, т.к. 2 поля ссылаются на 1 и тот же элемент
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseName", orphanRemoval = true)
//    private String notification;

    //   @ManyToMany(fetch = FetchType.EAGER)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Enumerated(EnumType.STRING)  //what does it mean?           !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", orphanRemoval = true)
    private Set<StudentTaskStatus> studentTaskStatusSet = new HashSet<>();

    @ManyToMany(mappedBy = "studentSet", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Course> studentCourseSet = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher", orphanRemoval = true)
    private Set<Course> teacherCourseSet = new HashSet<>();

    public User(){}

    public User(String name, String surname, String login, String password, String passwordConfirm,
                Set<Role> roles, Set<StudentTaskStatus> studentTaskStatusSet, Set<Course> studentCourseSet, Set<Course> teacherCourseSet) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
   //     this.notification = notification;
        this.roles = roles;
        this.studentTaskStatusSet = studentTaskStatusSet;
        this.studentCourseSet = studentCourseSet;
        this.teacherCourseSet = teacherCourseSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

//    public String getNotification() {
//        return notification;
//    }
//
//    public void setNotification(String notification) {
//        this.notification = notification;
//    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<StudentTaskStatus> getStudentTaskStatusSet() {
        return studentTaskStatusSet;
    }

    public void setStudentTaskStatusSet(Set<StudentTaskStatus> studentTaskStatusSet) {
        this.studentTaskStatusSet = studentTaskStatusSet;
    }

    public Set<Course> getStudentCourseSet() {
        return studentCourseSet;
    }

    public void setStudentCourseSet(Set<Course> studentCourseSet) {
        this.studentCourseSet = studentCourseSet;
    }

    public Set<Course> getTeacherCourseSet() {
        return teacherCourseSet;
    }

    public void setTeacherCourseSet(Set<Course> teacherCourseSet) {
        this.teacherCourseSet = teacherCourseSet;
    }
}
