package com.bsu.famcs.anytask.dto;

import com.bsu.famcs.anytask.entity.Course;
import com.bsu.famcs.anytask.entity.Role;
import com.bsu.famcs.anytask.entity.StudentTaskStatus;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
	private int id;
	private String name;
	private String surname;
	private String username;
	private boolean active;
	private String password;
	private String passwordConfirm;
	private Set<Role> roles = new HashSet<>();
	private Set<StudentTaskStatusDTO> studentTaskStatusSet = new HashSet<>();
	private Set<CourseDTO> studentCourseSet = new HashSet<>();
	private Set<CourseDTO> teacherCourseSet = new HashSet<>();

	public UserDTO() {

	}

	public UserDTO(int id, String name, String surname, String username,
	               boolean active, String password, String passwordConfirm,
	               Set <Role> roles, Set <StudentTaskStatusDTO> studentTaskStatusSet,
	               Set <CourseDTO> studentCourseSet, Set <CourseDTO> teacherCourseSet) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.active = active;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
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

	public Set <Role> getRoles() {
		return roles;
	}

	public void setRoles(Set <Role> roles) {
		this.roles = roles;
	}

	public Set <StudentTaskStatusDTO> getStudentTaskStatusSet() {
		return studentTaskStatusSet;
	}

	public void setStudentTaskStatusSet(Set <StudentTaskStatusDTO> studentTaskStatusSet) {
		this.studentTaskStatusSet = studentTaskStatusSet;
	}

	public Set <CourseDTO> getStudentCourseSet() {
		return studentCourseSet;
	}

	public void setStudentCourseSet(Set <CourseDTO> studentCourseSet) {
		this.studentCourseSet = studentCourseSet;
	}

	public Set <CourseDTO> getTeacherCourseSet() {
		return teacherCourseSet;
	}

	public void setTeacherCourseSet(Set <CourseDTO> teacherCourseSet) {
		this.teacherCourseSet = teacherCourseSet;
	}
}
