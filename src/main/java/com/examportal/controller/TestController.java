 package com.examportal.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.dto.QuestionDto;
import com.examportal.dto.TestDto;
import com.examportal.entity.Questions;
import com.examportal.entity.Test;
import com.examportal.entity.User;
import com.examportal.iservice.QuestionsService;
import com.examportal.iservice.UserService;
import com.examportal.repository.QuestionsRepository;
import com.examportal.repository.TestRepository;
import com.examportal.repository.UserRepository;
import com.examportal.serviceimpl.QuestionServiceImpl;
import com.examportal.serviceimpl.TestServiceImpl;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/tests")
public class TestController {

	@Autowired
	private TestServiceImpl testService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private TestRepository testRepository;

	@Autowired
	private UserService userService; 
	@Autowired
    private QuestionsService questionService;
	@Autowired
	private QuestionServiceImpl questionServiceImpl;
	@Autowired
	private QuestionsRepository questionsRepository;

	

	
//This Method is for user,to get Questions for writing the exam.
	
	 private static final int QUESTION_DURATION_MINUTES = 2;

	    @GetMapping("/user-test-questions/{userId}/{testId}")
	    public ResponseEntity<List<QuestionDto>> getQuestionsForEnrolledUser(@PathVariable int userId, @PathVariable int testId,  HttpSession sessionManager) {
	        Optional<User> opUser = userRepository.findById(userId);

	        if (opUser.isPresent()) {
	            User user = opUser.get();

	            boolean isEnrolled = user.getEnrolledTests().stream().anyMatch(test -> test.getTestId() == testId);

	            if (isEnrolled) {
	                // Check if the test is started
	                LocalDateTime testStartTime = (LocalDateTime) sessionManager.getAttribute("testStartTime");
	                if (testStartTime == null) {
	                    // Start the test
	                    sessionManager.setAttribute("testStartTime", LocalDateTime.now());
	                }

	                // Convert Questions to QuestionDto
	                List<QuestionDto> questionDtos = getQuestionsForTest(testId);

	                // Calculate remaining time for each question
	                int remainingTimeInSeconds = calculateRemainingTime(testStartTime, questionDtos.size());
	                sessionManager.setAttribute("remainingTimeInSeconds", remainingTimeInSeconds);

	                return ResponseEntity.ok(questionDtos);
	            } else {
	                throw new RuntimeException("User is not enrolled in the specified test");
	            }
	        }

	        throw new RuntimeException("User not found");
	    }
	    
	    private List<QuestionDto> getQuestionsForTest(int testId) {
	        Test test = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
	        List<Questions> questions = test.getQuestions();

	        return questions.stream()
	                .map(q -> new QuestionDto(q.getQuestionId(), q.getQuestion(), q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD()))
	                .collect(Collectors.toList());
	    }
	    @GetMapping("/findremainingtime")	
	    private int calculateRemainingTime(LocalDateTime testStartTime, int numberOfQuestions) {
	        if (testStartTime != null) {
	            LocalDateTime currentTime = LocalDateTime.now();
	            Duration duration = Duration.between(testStartTime, currentTime);

	            int totalTimeInSeconds = numberOfQuestions * QUESTION_DURATION_MINUTES * 60;
	            int elapsedTimeInSeconds = (int) duration.getSeconds();

	            return Math.max(totalTimeInSeconds - elapsedTimeInSeconds, 0);
	        }
	        return 0;
	    }
	
	   

}
