package com.examportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.dto.AdminLoginDto;
import com.examportal.dto.AvailableTestsDto;
import com.examportal.dto.QuestionDto;
import com.examportal.dto.TestDto;
import com.examportal.dto.UserDto;
import com.examportal.entity.Admin;
import com.examportal.entity.Questions;
import com.examportal.entity.User;
import com.examportal.exception.InvalidCredentialsException;
import com.examportal.exception.UserNotFoundException;
import com.examportal.iservice.QuestionsService;
import com.examportal.iservice.UserService;

import com.examportal.serviceimpl.AdminServiceImpl;
import com.examportal.serviceimpl.TestServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	

	@Autowired
	private TestServiceImpl testService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@Autowired
    private QuestionsService questionService;
	
	
	@GetMapping("/getallusers")
    public ResponseEntity<List<User>> getAllUser() {
    	return ResponseEntity.ok(userService.getAllUsers());
    	}
	

	@PutMapping("/addtest")
	public ResponseEntity<String> addTestWithQuestions(@RequestBody TestDto testDto) {
		testService.addTestWithQuestions(testDto);
		return ResponseEntity.ok("Test added successfully");
		}
	
	
	
	
	
	

    @GetMapping("/getallquestions")
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

 //------This Method is used to display Questions-with-answers of a particular test filtering by the testId, for Admin------ .   
    
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
    //-------Method is used to displaying all available tests--------.
   
    @GetMapping("/all-available-tests")
    public ResponseEntity<List<AvailableTestsDto>> getAllTests() {
        List<AvailableTestsDto> availableTests = testService.getAllTests();
        return ResponseEntity.ok(availableTests);
    }
        

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginDto adminLogin) {
        Admin admin = adminServiceImpl.login(adminLogin.getAdminMail(), adminLogin.getAdminPassword());

        if (admin != null) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
    
    
    
    @PutMapping("/{userId}")
	public ResponseEntity<String>updateUser(@PathVariable int userId,@RequestBody UserDto userDto){
	userService.updateUser(userId, userDto);
	return ResponseEntity.ok("User updated successfully");
	
}
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User successfully deleted");
        } catch (UserNotFoundException ex) {
            // Handle UserNotFoundException
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
        }
    }
     
   

	
}
  

