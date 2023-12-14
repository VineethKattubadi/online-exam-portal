package com.examportal.iservice;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.examportal.dto.QuestionDto;
import com.examportal.dto.ResultDto;
import com.examportal.dto.TestDto;
import com.examportal.entity.Questions;
import com.examportal.entity.Test;
import com.examportal.serviceimpl.TestResultDto;

public interface TestService  {
	
	public  List<Test> getAllTests();
	 public List<QuestionDto> getQuestionsForEnrolledUser(int userId, int testId);
	    public TestResultDto submitAndCalculateScore(int userId, int testId, Map<Integer, String> userSelectedOptions);


}
