package com.examportal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.dto.AvailableTestsDto;
import com.examportal.dto.TestEnrollDto;
import com.examportal.dto.TestResultDto;
import com.examportal.dto.UserDashBoardDto;
import com.examportal.dto.UserDto;
import com.examportal.dto.UserRegistrationDto;
import com.examportal.entity.User;
import com.examportal.exception.InvalidCredentialsException;
import com.examportal.iservice.TestService;
import com.examportal.iservice.UserResultsService;
import com.examportal.iservice.UserService;

@RestController // its is the combimation of @Controller and @Response body
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;

	}

	@Autowired
	private UserResultsService userResultsService;

	@Autowired
	private TestService testService;

//------Using this Method User is going to Login into the ExamPortal(check UserServiceimpl,how it works)----------.

	@GetMapping("/login/{userMail}/{userPassword}")
	public ResponseEntity<String> loginuser(@PathVariable String userMail, @PathVariable String userPassword) {
		User user = userService.loginuser(userMail, userPassword);
		try {

			if (user == null)
				throw new InvalidCredentialsException("Check your ");
			return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
		} catch (InvalidCredentialsException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

//-----This Method is used to Register into the Online Exam Portal(check UserServiceimpl,how it works)--------.

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto registrationRequest) {

		userService.registerUser(registrationRequest);
		return ResponseEntity.ok("Registration successful");

	}

//----------------This Method is For Updating User Profile(check UserServiceimpl,how it works)-------------------------

	@PutMapping(value = "/editprofile", consumes = "application/json")
	public ResponseEntity<String> editProfile(@RequestBody UserDto userDto) {
		
			userService.editProfile(userDto);
			return ResponseEntity.ok("Updated Successfully");
		
	}
//delete Account 	

//---This Method is Used to Get Enroll for the Available Tests(-------------- 

	@PostMapping("/enroll")
	public ResponseEntity<String> enrollUserInTest(@RequestBody TestEnrollDto testEnrollDto) {

		return ResponseEntity.ok(userService.enrollUserInTest(testEnrollDto.getUserMail(), testEnrollDto.getTestId()));
	}

//---------------This Method is for User to Submit the Answers and to Get The Results(check TestServiceImpl,how it works)--------------------- 

	@PostMapping("/submit-answers/{userId}/{testId}")
	public ResponseEntity<TestResultDto> submitAndCalculateScore(@PathVariable int userId, @PathVariable int testId,
			@RequestBody Map<Integer, String> userSelectedOptions) {

		TestResultDto result = testService.submitAndCalculateScore(userId, testId, userSelectedOptions);
		return ResponseEntity.ok(result);

	}

//------This Method is For user  to Start the Start (check UserServiceImpl,for logic)----------

	@PostMapping("/start-test")
	public ResponseEntity<String> startTest(@RequestBody TestEnrollDto testEnrollDto) {

		return ResponseEntity.ok(userService.startTest(testEnrollDto.getUserId(), testEnrollDto.getTestId()));

	}

//------------------ This Method is For User to view all available tests for user(check UserServiceImpl  ---------------

	@GetMapping("/all-available-tests")
	public ResponseEntity<List<AvailableTestsDto>> getAllTests() {
		List<AvailableTestsDto> availableTests = testService.getAllTests();
		return ResponseEntity.ok(availableTests);
	}

//------------------- Method to view UserDashBoard (check UserResultsServiceImpl for the logic)--------------------------	    

	@GetMapping("/dashboard/{userId}")
	public ResponseEntity<UserDashBoardDto> getUserDashboard(@PathVariable int userId) {
		UserDashBoardDto userDashboardDto = userResultsService.getUserDashBoard(userId);
	//	System.out.println();
		return ResponseEntity.ok(userDashboardDto);
	}

}
