package com.examportal.dto;

import java.util.List;

import com.examportal.dto.ResultDto;

public class TestResultDto {
	
	private int score;
	private int totalQuestions;
	
	private List<ResultDto> resultDtos;
	
	public TestResultDto() {}
	
	
	public TestResultDto(int score, int totalQuestions, List<ResultDto> resultDtos) {
		super();
		this.score = score;
		this.totalQuestions = totalQuestions;
		this.resultDtos = resultDtos;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}




	public int getTotalQuestions() {
		return totalQuestions;
	}


	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}


	public List<ResultDto> getResultDtos() {
		return resultDtos;
	}


	public void setResultDtos(List<ResultDto> resultDtos) {
		this.resultDtos = resultDtos;
	}



}
