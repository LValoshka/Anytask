package com.bsu.famcs.anytask.mapper;

import com.bsu.famcs.anytask.dto.CourseDTO;
import com.bsu.famcs.anytask.dto.TaskDTO;
import com.bsu.famcs.anytask.dto.UserDTO;
import com.bsu.famcs.anytask.entity.Course;
import com.bsu.famcs.anytask.entity.Task;
import com.bsu.famcs.anytask.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

	private UserMapper userMapper;
	private TaskMapper taskMapper;

	@Autowired
	public CourseMapper(UserMapper userMapper, TaskMapper taskMapper) {
		this.userMapper = userMapper;
		this.taskMapper = taskMapper;
	}

	public Course createEntityFromDTO(CourseDTO courseDTO) {
		Course course = new Course();
		course.setId(courseDTO.getId());
		course.setName(courseDTO.getName());
		course.setDescription(courseDTO.getDescription());
		Set<UserDTO> students = courseDTO.getStudentSet();
		if (students != null && !students.isEmpty()) {
			course.setStudentSet(students.stream()
					.map(userMapper::createEntityFromDTO)
					.collect(Collectors.toSet()));
		}
		Set<TaskDTO> tasks = courseDTO.getTaskSet();
		if(tasks != null && !tasks.isEmpty()) {
			course.setTaskSet(tasks.stream()
					.map(taskMapper::createEntityFromDTO)
					.collect(Collectors.toSet()));
		}

		return course;
	}

	public CourseDTO createDTOFromEntity(Course course) {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setId(course.getId());
		courseDTO.setName(course.getName());
		courseDTO.setDescription(course.getDescription());
		Set<User> students = course.getStudentSet();
		if (students != null && !students.isEmpty()) {
			courseDTO.setStudentSet(students.stream()
					.map(userMapper::createDTOFromEntity)
					.collect(Collectors.toSet()));
		}
		Set<Task> tasks = course.getTaskSet();
		if(tasks != null && !tasks.isEmpty()) {
			courseDTO.setTaskSet(tasks.stream()
					.map(taskMapper::createDTOFromEntity)
					.collect(Collectors.toSet()));
		}

		return courseDTO;
	}
}
