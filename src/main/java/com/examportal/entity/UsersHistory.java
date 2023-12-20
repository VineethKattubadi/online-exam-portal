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
import jakarta.persistence.Table;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "usersHistoryId")
@Table(name="user_History")
public class UsersHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JoinColumn(name="users_history_id")
	private int usersHistoryId;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User users;
	@OneToMany(mappedBy="usersHistory")
	private List<Test> testManagement;
	@OneToMany(mappedBy="usersHistory")
	private List<UserResults> userResults;
	public UsersHistory() {
		
	}
	public UsersHistory(int usersHistoryId, User users, List<Test> testManagement, List<UserResults> userResults) {
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
	public List<Test> getTestManagement() {
		return testManagement;
	}
	public void setTestManagement(List<Test> testManagement) {
		this.testManagement = testManagement;
	}
	public List<UserResults> getUserResults() {
		return userResults;
	}
	public void setUserResults(List<UserResults> userResults) {
		this.userResults = userResults;
	}
	

}
