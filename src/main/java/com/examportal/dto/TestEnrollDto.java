package com.examportal.dto;

public class TestEnrollDto {
	private int testId;
	private String userMail;
	private int userId;
	

	public TestEnrollDto() {
		super();
	}

	public TestEnrollDto(int testId, String userMail,int userId) {
		super();
		this.testId = testId;
		this.userMail = userMail;
		this.testId = testId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TestEnrollDto [testId=" + testId + ", userMail=" + userMail + ", userId=" + userId + "]";
	}

	
}
