package com.bsu.famcs.anytask.dto;

import java.util.HashSet;
import java.util.Set;

public class CourseDTO {
	private int id;
	private String name;
	private String description;
	private UserDTO teacher;
	private Set<UserDTO> studentSet = new HashSet<>();
	private Set<TaskDTO> taskSet = new HashSet<>();

	public CourseDTO() {

	}

	public CourseDTO(int id, String name, String description, UserDTO teacher,
	                 Set <UserDTO> studentSet, Set <TaskDTO> taskSet) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.teacher = teacher;
		this.studentSet = studentSet;
		this.taskSet = taskSet;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserDTO getTeacher() {
		return teacher;
	}

	public void setTeacher(UserDTO teacher) {
		this.teacher = teacher;
	}

	public Set <UserDTO> getStudentSet() {
		return studentSet;
	}

	public void setStudentSet(Set <UserDTO> studentSet) {
		this.studentSet = studentSet;
	}

	public Set <TaskDTO> getTaskSet() {
		return taskSet;
	}

	public void setTaskSet(Set <TaskDTO> taskSet) {
		this.taskSet = taskSet;
	}
}
