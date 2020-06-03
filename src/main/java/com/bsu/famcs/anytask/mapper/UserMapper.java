package com.bsu.famcs.anytask.mapper;

import com.bsu.famcs.anytask.dto.CourseDTO;
import com.bsu.famcs.anytask.dto.StudentTaskStatusDTO;
import com.bsu.famcs.anytask.dto.UserDTO;
import com.bsu.famcs.anytask.entity.Course;
import com.bsu.famcs.anytask.entity.StudentTaskStatus;
import com.bsu.famcs.anytask.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

	private StudentTaskStatusMapper studentTaskStatusMapper;

	@Autowired
	public UserMapper(StudentTaskStatusMapper studentTaskStatusMapper) {
		this.studentTaskStatusMapper = studentTaskStatusMapper;
	}

	public User createEntityFromDTO(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setUsername(userDTO.getUsername());
		user.setActive(userDTO.isActive());
		user.setPassword(userDTO.getPassword());
		user.setPasswordConfirm(userDTO.getPasswordConfirm());
		user.setRoles(userDTO.getRoles());
		Set<StudentTaskStatusDTO> studentTaskStatusSet = userDTO.getStudentTaskStatusSet();
		if (studentTaskStatusSet != null && !studentTaskStatusSet.isEmpty()) {
			user.setStudentTaskStatusSet(studentTaskStatusSet.stream()
					.map(studentTaskStatusMapper::createEntityFromDTO)
					.collect(Collectors.toSet()));
		}
		Set<CourseDTO> courseSet = userDTO.getStudentCourseSet();
		if (courseSet != null && !courseSet.isEmpty()) {
			user.setStudentCourseSet(courseSet.stream()
					.map(this::createCourseEntityFromDTO)
					.collect(Collectors.toSet()));
		}
		Set<CourseDTO> teacherSet = userDTO.getTeacherCourseSet();
		if (teacherSet != null && !teacherSet.isEmpty()) {
			user.setTeacherCourseSet(teacherSet.stream()
					.map(this::createCourseEntityFromDTO)
					.collect(Collectors.toSet()));
		}
		return user;
	}

	public UserDTO createDTOFromEntity(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setSurname(user.getSurname());
		userDTO.setUsername(user.getUsername());
		userDTO.setActive(user.isActive());
		userDTO.setPassword(user.getPassword());
		userDTO.setPasswordConfirm(user.getPasswordConfirm());
		userDTO.setRoles(user.getRoles());
		Set<StudentTaskStatus> studentTaskStatusSet = user.getStudentTaskStatusSet();
		if (studentTaskStatusSet != null && !studentTaskStatusSet.isEmpty()) {
			userDTO.setStudentTaskStatusSet(studentTaskStatusSet.stream()
					.map(studentTaskStatusMapper::createDTOFromEntity)
					.collect(Collectors.toSet()));
		}
		Set<Course> courseSet = user.getStudentCourseSet();
		if (courseSet != null && !courseSet.isEmpty()) {
			userDTO.setStudentCourseSet(courseSet.stream()
					.map(this::createCourseDTOFromEntity)
					.collect(Collectors.toSet()));
		}
		Set<Course> teacherSet = user.getTeacherCourseSet();
		if (teacherSet != null && !teacherSet.isEmpty()) {
			userDTO.setTeacherCourseSet(teacherSet.stream()
					.map(this::createCourseDTOFromEntity)
					.collect(Collectors.toSet()));
		}
		return userDTO;
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
