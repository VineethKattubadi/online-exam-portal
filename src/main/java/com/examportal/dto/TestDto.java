package com.examportal.dto;

import java.util.List;

import com.examportal.entity.Admin;
import com.examportal.entity.Questions;
import com.examportal.entity.User;
import com.examportal.entity.UserResults;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class TestDto {

	private int testId;
	private char courseType;
	private List<QuestionDto> questions;
	private List<AnswerDto> answers;
	public TestDto() {
		super();
	}
	public TestDto(int testId, char courseType, List<QuestionDto> questions, List<AnswerDto> answers) {
		super();
		this.testId = testId;
		this.courseType = courseType;
		this.questions = questions;
		this.answers = answers;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public char getCourseType() {
		return courseType;
	}
	public void setCourseType(char courseType) {
		this.courseType = courseType;
	}
	public List<QuestionDto> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDto> questions) {
		this.questions = questions;
	}
	public List<AnswerDto> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerDto> answers) {
		this.answers = answers;
	}
	

}
