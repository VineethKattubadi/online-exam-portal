package com.examportal.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {
	@Id
	@Column(name = "user_id")
	private int userId;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_mail")
	private String userMail;
	@Column(name = "user_mobile_no")
	private long userMobileNo;
	@Column(name = "user_address")
	private String userAddress;
	@Column(name = "user_password")
	private String userPassword;
	// @OneToOne
	// @JoinColumn(name="user_address_id")
	// UserAddress userAddress;
	@ManyToOne
	private Admin admin;
	@ManyToMany
	private List<Test> test;
	@OneToMany(mappedBy = "user")
	private List<UserResults> userResults;

	@OneToOne
	private UsersHistory usersHistory;
	
	

	public User(int userId, String userName, String userMail, String userPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userMail = userMail;
		this.userPassword = userPassword;
	}

	@ManyToMany
	@JoinTable(name = "user_enrolled_tests", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "test_id"))
	private List<Test> enrolledTests = new ArrayList<>();
	

	public User() {

	}

	public User(int userId, String userName, String userMail, long userMobileNo, String userAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userMail = userMail;
		this.userMobileNo = userMobileNo;
		this.userAddress = userAddress;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public long getUserMobileNo() {
		return userMobileNo;
	}

	public void setUserMobileNo(long userMobileNo) {
		this.userMobileNo = userMobileNo;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public List<UserResults> getUserResults() {
		return userResults;
	}

	public void setUserResults(List<UserResults> userResults) {
		this.userResults = userResults;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Test> getTestManagement() {
		return test;
	}

	public void setTestManagement(List<Test> testManagement) {
		this.test = testManagement;
	}

	public UsersHistory getUsersHistory() {
		return usersHistory;
	}

	public void setUsersHistory(UsersHistory usersHistory) {
		this.usersHistory = usersHistory;
	}

	public List<Test> getTest() {
		return test;
	}

	public void setTest(List<Test> test) {
		this.test = test;
	}

	public List<Test> getEnrolledTests() {
		return enrolledTests;
	}

	public void setEnrolledTests(List<Test> enrolledTests) {
		this.enrolledTests = enrolledTests;
	}



}
