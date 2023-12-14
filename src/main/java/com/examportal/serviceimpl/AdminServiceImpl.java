package com.examportal.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.examportal.dto.ResultDto;
import com.examportal.entity.Questions;
import com.examportal.entity.Test;
import com.examportal.entity.User;
import com.examportal.entity.UserResults;
import com.examportal.iservice.AdminService;
import com.examportal.repository.TestRepository;
import com.examportal.repository.UserRepository;
import com.examportal.repository.UserResultsRepository;

public class AdminServiceImpl  implements AdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestRepository testRepository;
	
	@Autowired
	private UserResultsRepository userResultsRepository;
	
	
	
	
	public List<ResultDto> getUserResults(int userId, int testId) {
	    Optional<User> opUser = userRepository.findById(userId);

	    if (opUser.isPresent()) {
	        User user = opUser.get();

	        boolean isEnrolled = user.getEnrolledTests().stream().anyMatch(test -> test.getTestId() == testId);

	        if (isEnrolled) {
	            Test test = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));

	            // Fetch user results
	            UserResults userResults = userResultsRepository.findByUserAndTest(user, test)
	                    .orElseThrow(() -> new RuntimeException("User results not found"));

	            // Get user selected options
	            Map<Integer, String> userSelectedOptions = userResults.getUserSelectedOptions();

	            // Fetch questions for the test
	            List<Questions> questions = test.getQuestions();

	            // Compare user selected options with correct answers
	            List<ResultDto> resultDtos = questions.stream()
	                    .map(question -> {
	                        String userSelectedOption = userSelectedOptions.get(question.getQuestionId());
	                        boolean isCorrect = question.getAnswer().equals(userSelectedOption);
	                        return new ResultDto(question.getQuestionId(), question.getQuestion(),
	                                question.getAnswer(), userSelectedOption, isCorrect);
	                    })
	                    .collect(Collectors.toList());

	            return resultDtos;
	        } else {
	            throw new RuntimeException("User is not enrolled in the specified test");
	        }
	    }

	    throw new RuntimeException("User not found");
	}


}
