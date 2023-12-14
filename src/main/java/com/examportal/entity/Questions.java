package com.examportal.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="questions")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "questionId")
public class Questions {
	@Id
	@Column(name="question_id")
	private int questionId;
	
	@Column(name = "question")
	private String question;
	
	@Column(name="option_a")
	private String optionA;
	
	@Column(name="option_b")
	private String optionB;
	
	@Column(name="option_c")
	private String optionC;
	
	@Column(name="option_d")
	private String optionD;
	//@Column(name="selected_option")
	//private String selectedOption;
	@Column(name = "answer")
	private String answer;
	
	// In Questions entity
	@ManyToOne
	@JoinColumn(name = "test_id")  // Make sure the name matches the column name in the Test entity
	private Test test;

	public Questions() {
		super();
	}

	public Questions(int questionId,String question, String optionA, String optionB, String optionC, String optionD, String answer,
			Test test) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.answer = answer;
		this.test = test;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

//	public String getSelectedOption() {
//		return selectedOption;
//	}
//
//	public void setSelectedOption(String selectedOption) {
//		this.selectedOption = selectedOption;
//	}
}
