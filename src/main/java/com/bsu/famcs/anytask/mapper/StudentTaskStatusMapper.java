package com.bsu.famcs.anytask.mapper;

import com.bsu.famcs.anytask.dto.StudentTaskStatusDTO;
import com.bsu.famcs.anytask.entity.StudentTaskStatus;
import com.bsu.famcs.anytask.entity.Task;
import com.bsu.famcs.anytask.entity.User;
import org.springframework.stereotype.Component;

@Component
public class StudentTaskStatusMapper {
	public StudentTaskStatus createEntityFromDTO(StudentTaskStatusDTO studentTaskStatusDTO) {
		StudentTaskStatus studentTaskStatus = new StudentTaskStatus();
		studentTaskStatus.setId(studentTaskStatusDTO.getId());
		User student = new User();
		if (studentTaskStatusDTO.getStudent() != null) {
			student.setId(studentTaskStatusDTO.getId());
			studentTaskStatus.setStudent(student);
		}
		Task task = new Task();
		if (studentTaskStatusDTO.getTask() != null) {
			task.setId(studentTaskStatusDTO.getId());
			studentTaskStatus.setTask(task);
		}
		studentTaskStatus.setLabel(studentTaskStatusDTO.getLabel());
		studentTaskStatus.setStartDate(studentTaskStatusDTO.getStartDate());
		studentTaskStatus.setEndDate(studentTaskStatusDTO.getEndDate());
		studentTaskStatus.setMark(studentTaskStatusDTO.getMark());
		studentTaskStatus.setUrl(studentTaskStatusDTO.getUrl());
		return studentTaskStatus;
	}

	public StudentTaskStatusDTO createDTOFromEntity(StudentTaskStatus studentTaskStatus) {
		StudentTaskStatusDTO studentTaskStatusDTO = new StudentTaskStatusDTO();
		studentTaskStatusDTO.setId(studentTaskStatus.getId());
		User student = new User();
		if (studentTaskStatus.getStudent() != null) {
			student.setId(studentTaskStatus.getId());
			studentTaskStatusDTO.setStudent(student);
		}
		Task task = new Task();
		if (studentTaskStatus.getTask() != null) {
			task.setId(studentTaskStatus.getId());
			studentTaskStatusDTO.setTask(task);
		}
		studentTaskStatusDTO.setLabel(studentTaskStatus.getLabel());
		studentTaskStatusDTO.setStartDate(studentTaskStatus.getStartDate());
		studentTaskStatusDTO.setEndDate(studentTaskStatus.getEndDate());
		studentTaskStatusDTO.setMark(studentTaskStatus.getMark());
		studentTaskStatusDTO.setUrl(studentTaskStatus.getUrl());
		return studentTaskStatusDTO;
	}
}
