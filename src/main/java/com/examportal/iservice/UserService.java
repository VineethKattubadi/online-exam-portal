package com.examportal.iservice;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.examportal.dto.QuestionDto;
import com.examportal.dto.TestDto;
import com.examportal.dto.TestEnrollDto;
import com.examportal.dto.UserDashBoardDto;
import com.examportal.dto.UserDto;
import com.examportal.dto.UserLoginDto;
import com.examportal.dto.UserRegistrationDto;
import com.examportal.entity.Questions;
import com.examportal.entity.Test;
//import com.examportal.dto.UserRegistrationDto;
import com.examportal.entity.User;

public interface UserService {
	
	
	User loginuser(String userMail,String userpassword);
	
	public void registerUser(UserRegistrationDto registrationRequest);
	public User editProfile(UserDto userDto);
	
	String enrollUserInTest(String userMail, int testId);
	
	 public void enrollUserInTest(int userId, int testId);
	 
	
	public String startTest(int userId, int testId);
	
	//user attempting the test
    public List<Questions> getAllQuestions(int questionId, String question, String optionA, String optionB, String optionC, String optionD);
	public void submitUserAnswers(int userId, int testId, Map<Integer, String> userSelectedOptions);

	public List<TestDto> getAllAvailableTestsforUser();
	List<User> getAllUsers();
	
	public void updateUser(int userId, UserDto userDto);
	
	public void deleteUser(Integer userId);
	


}
