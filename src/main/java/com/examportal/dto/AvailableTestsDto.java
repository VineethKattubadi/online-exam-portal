package com.examportal.dto;

import jakarta.persistence.Column;

public class AvailableTestsDto {
	
	private int testId;
	private String courseType;
	
	
	public AvailableTestsDto() {
		
	}

	public AvailableTestsDto(int testId, String courseType) {
		super();
		this.testId = testId;
		this.courseType = courseType;
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

	@Override
	public String toString() {
		return "AvailableTestsDto [testId=" + testId + ", courseType=" + courseType + "]";
	}

}
