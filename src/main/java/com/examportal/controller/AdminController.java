package com.examportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.dto.QuestionDto;
import com.examportal.dto.ResultDto;
import com.examportal.dto.TestDto;
import com.examportal.entity.Questions;
import com.examportal.entity.Test;
import com.examportal.entity.User;
import com.examportal.iservice.QuestionsService;
import com.examportal.iservice.TestService;
import com.examportal.iservice.UserService;
import com.examportal.repository.QuestionsRepository;
import com.examportal.repository.TestRepository;
import com.examportal.serviceimpl.QuestionServiceImpl;
import com.examportal.serviceimpl.TestServiceImpl;
import com.examportal.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private TestServiceImpl testService;
	
	@Autowired
	private TestRepository testRepository;

	@Autowired
	private UserService userService;


	@GetMapping("/getallusers")
    public ResponseEntity<List<User>> getAllUsers() {
    	return ResponseEntity.ok(userService.getAllUsers());
    	}
	

	@PostMapping("/addtest")
	public ResponseEntity<String> addTestWithQuestions(@RequestBody TestDto testDto) {
		testService.addTestWithQuestions(testDto);
		return ResponseEntity.ok("Test added successfully");
		}
	
	
	@GetMapping("/alltests")
	public ResponseEntity<List<Test>> getAllTests() {
		List<Test> tests = testService.getAllTests();
		return ResponseEntity.ok(tests);
		}
	
	
	@Autowired
    private QuestionsService questionService;
	@Autowired
	private QuestionServiceImpl questionServiceImpl;
	@Autowired
	private QuestionsRepository questionsRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<Questions>> getAllQuestions(
            @RequestParam(required = false) Integer questionId,
            @RequestParam(required = false) String question,
            @RequestParam(required = false) String optionA,
            @RequestParam(required = false) String optionB,
            @RequestParam(required = false) String optionC,
            @RequestParam(required = false) String optionD) {

        List<Questions> questions = questionService.getAllQuestions(questionId, question, optionA, optionB, optionC, optionD);

        return ResponseEntity.ok(questions);
    }

    @GetMapping("/tests/{testId}/questions")
    public ResponseEntity<List<QuestionDto>> getQuestionsByTestId(@PathVariable int testId) {
        List<QuestionDto> questions = testService.getQuestionsByTestId(testId);

        if (questions != null && !questions.isEmpty()) {
            return ResponseEntity.ok(questions);
        } else {
            // Handle the case where no questions are found
            return ResponseEntity.notFound().build();
        }
        
}
    //results displaying
   

        

       
    

}
