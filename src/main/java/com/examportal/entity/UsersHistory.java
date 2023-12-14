package com.examportal.entity;

import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "usersHistoryId")
public class UsersHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JoinColumn(name="users_history_id")
	private int usersHistoryId;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User users;
	@OneToOne
	@JoinColumn(name="test_id")
	private Test testManagement;
	@OneToOne
	@JoinColumn(name="exam_score")
	private UserResults userResults;
	public UsersHistory() {
		
	}
	public UsersHistory(int usersHistoryId, User users, Test testManagement, UserResults userResults) {
		super();
		this.usersHistoryId = usersHistoryId;
		this.users = users;
		this.testManagement = testManagement;
		this.userResults = userResults;
	}
	public int getUsersHistoryId() {
		return usersHistoryId;
	}
	public void setUsersHistoryId(int usersHistoryId) {
		this.usersHistoryId = usersHistoryId;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	public Test getTestManagement() {
		return testManagement;
	}
	public void setTestManagement(Test testManagement) {
		this.testManagement = testManagement;
	}
	public UserResults getUserResults() {
		return userResults;
	}
	public void setUserResults(UserResults userResults) {
		this.userResults = userResults;
	}
	

}
