package com.bsu.famcs.anytask.dto;

public class TaskDTO {
	private int id;
	private String name;
	private String dueDate;
	private String description;
	private CourseDTO courseDTO;
	private StudentTaskStatusDTO studentTaskStatusDTO;

	public TaskDTO() {

	}

	public TaskDTO(int id, String name, String dueDate, String description,
	               CourseDTO courseDTO, StudentTaskStatusDTO studentTaskStatusDTO) {
		this.id = id;
		this.name = name;
		this.dueDate = dueDate;
		this.description = description;
		this.courseDTO = courseDTO;
		this.studentTaskStatusDTO = studentTaskStatusDTO;
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

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CourseDTO getCourseDTO() {
		return courseDTO;
	}

	public void setCourseDTO(CourseDTO courseDTO) {
		this.courseDTO = courseDTO;
	}

	public StudentTaskStatusDTO getStudentTaskStatusDTO() {
		return studentTaskStatusDTO;
	}

	public void setStudentTaskStatusDTO(StudentTaskStatusDTO studentTaskStatusDTO) {
		this.studentTaskStatusDTO = studentTaskStatusDTO;
	}
}
