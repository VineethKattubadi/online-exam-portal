package com.examportal.controller;

import java.util.List;
import java.util.Map;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.dto.TestDto;
import com.examportal.dto.TestEnrollDto;
import com.examportal.dto.UserDto;
import com.examportal.dto.UserLoginDto;
import com.examportal.dto.UserRegistrationDto;
import com.examportal.entity.Test;
//import com.examportal.dto.UserRegistrationDto;
import com.examportal.entity.User;
import com.examportal.exception.InvalidCredentialsException;
import com.examportal.iservice.TestService;
import com.examportal.iservice.UserService;
import com.examportal.repository.UserResultsRepository;
import com.examportal.serviceimpl.TestResultDto;
import com.examportal.serviceimpl.UserServiceImpl;





@RestController        //its is the combimation of @Controller and @Response body
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
		
	}
	@Autowired
	private UserResultsRepository userResultsRepository;

	@GetMapping("/login")
	public ResponseEntity<String> loginuser(@RequestParam String userMail, @RequestParam String userPassword) {
		User user = userService.loginuser(userMail, userPassword);
		try {
			// You may return the user object or a token depending on your authentication
			// strategy
			if (user == null)
				throw new InvalidCredentialsException("Check your ");
			return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
		} catch (InvalidCredentialsException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto registrationRequest) {
		try {
			userService.registerUser(registrationRequest);
			return ResponseEntity.ok("Registration successful");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
		}

	}

//		 @GetMapping("/{userMail}")
//		 public ResponseEntity<UserLoginDto> getUserProfile(@PathVariable String userMail){
//			 UserLoginDto userProfile = userService.getUserProfile(userMail);
//			 return new ResponseEntity<>(userProfile,HttpStatus.OK);
//			 
	// }
	@PutMapping(value = "/editprofile", consumes = "application/json")
		 public ResponseEntity<String> editProfile(@RequestBody UserDto userDto) {
			 try {
		     userService.editProfile(userDto);
		     return  ResponseEntity.ok("Updated Successfully");
			 }catch(Exception e) {
				 e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
				 
			 }
	
		   }
	  @GetMapping("/available-tests")
	    public ResponseEntity<List<TestDto>> getAvailableTests() {
//	        List<TestDto> availableTests = userService.getAllAvailableTests();
//	        return ResponseEntity.ok(availableTests);
		  return ResponseEntity.ok(null);
	    }

	    @PostMapping("/enroll")
	    public ResponseEntity<String> enrollUserInTest(@RequestBody TestEnrollDto testEnrollDto) {
	    	
	        
	        return ResponseEntity.ok(userService.enrollUserInTest(testEnrollDto.getUserMail(), testEnrollDto.getTestId()));
	    }
	    
	    
	    @Autowired
	    private TestService testService;

	    @PostMapping("/submit-answers")
	    public ResponseEntity<TestResultDto> submitAndCalculateScore(
	            @RequestParam int userId,
	            @RequestParam int testId,
	            @RequestBody Map<Integer, String> userSelectedOptions) {
	        try {
	            TestResultDto result = testService.submitAndCalculateScore(userId, testId, userSelectedOptions);
	            return ResponseEntity.ok(result);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }
	    }

//	    @PostMapping("/start-test")
//	    public ResponseEntity<String> startTest(@RequestBody TestEnrollDto testEnrollDto) {
//	    	if(){
//	        userService.startTest(testEnrollDto.getUserId(), testEnrollDto.getTestId());
//	        //System.out.println(ResponseEntity.ok("Test started")); 
//	        return ResponseEntity.ok("Test started");
//	    }
//	    	else {
//				return (ResponseEntity.badRequest().body("Cannot start the test, please enroll"));
//
//	    	}
//	    }

//	    }
	    @PostMapping("/start-test")
	    public ResponseEntity<String> startTest(@RequestBody TestEnrollDto testEnrollDto) {
	    	
	    	return ResponseEntity.ok(userService.startTest(testEnrollDto.getUserId(), testEnrollDto.getTestId()));

	       
	          
	    }







}
