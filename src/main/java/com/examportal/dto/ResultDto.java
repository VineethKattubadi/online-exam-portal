package com.examportal.dto;

import java.util.List;

public class ResultDto {
  
	private int questionId;
    private String question;
    private String correctAnswer;
    private String userSelectedOption;
    private boolean isCorrect;
    

    // Constructors, getters, and setters...
    public ResultDto() {
  		super();
  	}


	public ResultDto(int questionId, String question, String correctAnswer, String userSelectedOption,
			boolean isCorrect) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.userSelectedOption = userSelectedOption;
		this.isCorrect = isCorrect;
	}


	public int getQuestionId() {
		return questionId;
	}


	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getCorrectAnswer() {
		return correctAnswer;
	}


	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}


	public String getUserSelectedOption() {
		return userSelectedOption;
	}


	public void setUserSelectedOption(String userSelectedOption) {
		this.userSelectedOption = userSelectedOption;
	}


	public boolean isCorrect() {
		return isCorrect;
	}


	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
    
}
