package com.bsu.famcs.anytask.dto;

import com.bsu.famcs.anytask.entity.Label;
import com.bsu.famcs.anytask.entity.Task;
import com.bsu.famcs.anytask.entity.User;

public class StudentTaskStatusDTO {
	private int id;
	private User student;
	private Task task;
	private Label label;
	private String startDate;
	private String endDate;
	private int mark;
	private String url;

	public StudentTaskStatusDTO() {

	}

	public StudentTaskStatusDTO(int id, User student, Task task, Label label,
	                            String startDate, String endDate, int mark, String url) {
		this.id = id;
		this.student = student;
		this.task = task;
		this.label = label;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
