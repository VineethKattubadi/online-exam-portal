package com.examportal.entity;

import java.util.List;

import com.examportal.dto.QuestionDto;
import com.examportal.dto.TestDto;
//import com.examportal.serviceimpl.ArrayList;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "testId")
public class Test {
	@Id
	@Column(name="test_id")
	private int testId;
	
	@Column(name="course_Type")
	private String courseType;
	
	@ManyToOne
	private Admin admin;
	// In Test entity
	@OneToMany(mappedBy = "test")
	private List<Questions> questions;

	
	@OneToMany(mappedBy="test")
	private List<UserResults> userResults;
	
	@ManyToMany(mappedBy="test")
	private List<User> users;
	@ManyToOne
	private UsersHistory usersHistory;
	
	

	public Test() {
		super();
	}

	public Test(int testId, String courseType, Admin admin, List<Questions> questions, List<UserResults> userResults,
			List<User> users) {
		super();
		this.testId = testId;
		this.courseType = courseType;
		this.admin = admin;
		this.questions = questions;
		this.userResults = userResults;
		this.users = users;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	public List<UserResults> getUserResults() {
		return userResults;
	}

	public void setUserResults(List<UserResults> userResults) {
		this.userResults = userResults;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UsersHistory getUsersHistory() {
		return usersHistory;
	}

	public void setUsersHistory(UsersHistory usersHistory) {
		this.usersHistory = usersHistory;
	}

	public Test(int testId, String courseType) {
		super();
		this.testId = testId;
		this.courseType = courseType;
	}

	
	
	
	 
	
	
	
}















