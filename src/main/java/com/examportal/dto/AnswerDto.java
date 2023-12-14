package com.examportal.dto;

public class AnswerDto {
	private int testId;
	private int questioId;
	private String answer;
	public AnswerDto() {
		super();
	}
	public AnswerDto(int testId, int questioId, String answer) {
		super();
		this.testId = testId;
		this.questioId = questioId;
		this.answer = answer;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getQuestioId() {
		return questioId;
	}
	public void setQuestioId(int questioId) {
		this.questioId = questioId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "AnswerDto [testId=" + testId + ", questioId=" + questioId + ", answer=" + answer + "]";
	}
	

}
