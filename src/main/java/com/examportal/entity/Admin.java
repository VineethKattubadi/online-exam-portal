package com.examportal.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="admin")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "adminId")
public class Admin {
	@Id
	@Column(name="admin_id")
	private int adminId;
	@Column(name="admin_name")
	private String adminName;
	@Column(name="admin_address")
	private String adminAddress;
	@Column(name="admin_mail")
	private String adminMail;
	@Column(name="admin_password")
	private String adminPassword;
	@OneToMany(mappedBy="admin")
	 private List<User> user = new ArrayList<>();
	@OneToMany(mappedBy="admin")
	private List<Test> testManagement;
	
	
	public Admin() {
		
	}


	public Admin(int adminId, String adminName, String adminAddress, String adminMail, String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminAddress = adminAddress;
		this.adminMail = adminMail;
		this.adminPassword = adminPassword;
	}


	public int getAdminId() {
		return adminId;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}


	public String getAdminName() {
		return adminName;
	}


	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}


	public String getAdminAddress() {
		return adminAddress;
	}


	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}


	public String getAdminMail() {
		return adminMail;
	}


	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}


	public String getAdminPassword() {
		return adminPassword;
	}


	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}


	public List<User> getUser() {
		return user;
	}


	public void setUser(List<User> user) {
		this.user = user;
	}


	public List<Test> getTestManagement() {
		return testManagement;
	}


	public void setTestManagement(List<Test> testManagement) {
		this.testManagement = testManagement;
	}


	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminAddress=" + adminAddress
				+ ", adminMail=" + adminMail + ", adminPassword=" + adminPassword + ", user=" + user
				+ ", testManagement=" + testManagement + "]";
	}

	

	
	

}
