package com.bsu.famcs.anytask.mapper;

import com.bsu.famcs.anytask.dto.CourseDTO;
import com.bsu.famcs.anytask.dto.StudentTaskStatusDTO;
import com.bsu.famcs.anytask.dto.TaskDTO;
import com.bsu.famcs.anytask.entity.Course;
import com.bsu.famcs.anytask.entity.StudentTaskStatus;
import com.bsu.famcs.anytask.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

	private StudentTaskStatusMapper studentTaskStatusMapper;

	public TaskMapper(StudentTaskStatusMapper studentTaskStatusMapper) {
		this.studentTaskStatusMapper = studentTaskStatusMapper;
	}

	public Task createEntityFromDTO(TaskDTO taskDTO) {
		Task task = new Task();
		task.setId(taskDTO.getId());
		task.setName(taskDTO.getName());
		task.setDueDate(taskDTO.getDueDate());
		task.setDescription(taskDTO.getDescription());
		CourseDTO courseDTO = taskDTO.getCourseDTO();
		if (courseDTO != null) {
			task.setCourse(createCourseEntityFromDTO(courseDTO));
		}
		StudentTaskStatusDTO studentTaskStatusDTO = taskDTO.getStudentTaskStatusDTO();
		if (studentTaskStatusDTO != null) {
			task.setStudentTaskStatus(
					studentTaskStatusMapper.createEntityFromDTO(studentTaskStatusDTO));
		}

		return task;
	}

	public TaskDTO createDTOFromEntity(Task task) {
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(task.getId());
		taskDTO.setName(task.getName());
		taskDTO.setDueDate(task.getDueDate());
		taskDTO.setDescription(task.getDescription());
		Course course = task.getCourse();
		if (course != null) {
			taskDTO.setCourseDTO(createCourseDTOFromEntity(course));
		}
		StudentTaskStatus studentTaskStatus = task.getStudentTaskStatus();
		if (studentTaskStatus != null) {
			taskDTO.setStudentTaskStatusDTO(
					studentTaskStatusMapper.createDTOFromEntity(studentTaskStatus));
		}

		return taskDTO;
	}

	private Course createCourseEntityFromDTO(CourseDTO courseDTO) {
		Course course = new Course();
		course.setId(courseDTO.getId());
		course.setName(courseDTO.getName());
		course.setDescription(courseDTO.getDescription());
		return course;
	}

	private CourseDTO createCourseDTOFromEntity(Course course) {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setId(course.getId());
		courseDTO.setName(course.getName());
		courseDTO.setDescription(course.getDescription());
		return courseDTO;
	}
}
