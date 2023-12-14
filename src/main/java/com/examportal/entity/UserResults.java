package com.examportal.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="user_results")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "resultId")
public class UserResults{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int resultId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="exam_date")
	private LocalDate examDate;
		
	@Column(name="exam_score")
	private int examScore;
	
	@ManyToOne
	@JoinColumn(name="test_id")
	private Test test;
	
	@ElementCollection
    @CollectionTable(name = "user_result_mapping", joinColumns = @JoinColumn(name = "user_result_id"))
    @MapKeyColumn(name = "question_id")
    @Column(name = "selected_option")
    private Map<Integer, String> userSelectedOptions;
 
	public UserResults() {
		super();
	}

	public UserResults(int resultId, User user, LocalDate examDate, int examScore, Test test) {
		super();
		this.resultId = resultId;
		this.user = user;
		this.examDate = examDate;
		this.examScore = examScore;
		this.test = test;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getExamDate() {
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}

	public int getExamScore() {
		return examScore;
	}

	public void setExamScore(int examScore) {
		this.examScore = examScore;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Map<Integer, String> getUserSelectedOptions() {
		return userSelectedOptions;
	}

	public void setUserSelectedOptions(Map<Integer, String> userSelectedOptions) {
		this.userSelectedOptions = userSelectedOptions;
	}

	@Override
	public String toString() {
		return "UserResults [resultId=" + resultId + ", user=" + user.getUserId() + ", examDate=" + examDate + ", examScore="
				+ examScore + ", test=" + test + "]";
	}
	
	
	
}