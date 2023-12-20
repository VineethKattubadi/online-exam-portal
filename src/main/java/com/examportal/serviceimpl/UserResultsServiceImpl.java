package com.examportal.serviceimpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.dto.AvailableTestsDto;
import com.examportal.dto.UserDashBoardDto;
import com.examportal.entity.Test;
import com.examportal.entity.User;
import com.examportal.entity.UserResults;
import com.examportal.exception.UserNotFoundException;
import com.examportal.iservice.UserResultsService;
import com.examportal.repository.TestRepository;
import com.examportal.repository.UserRepository;
import com.examportal.repository.UserResultsRepository;
@Service
public class UserResultsServiceImpl implements UserResultsService {
	
	 @Autowired
	    private UserResultsRepository userResultsRepository;
	 
	 @Autowired
	    private TestRepository testRepository;

	 @Autowired
	 private UserRepository userRepository;

//	------- Logic for UserDashBoard (check UserController for HTTP RequestMapping)-----
	 @Override
	 public UserDashBoardDto getUserDashBoard(int userId) {
	     // Fetch user details
	     User user = userRepository.findById(userId)
	             .orElseThrow(() -> new UserNotFoundException("User not found with Id:" + userId));

	     // Fetch user exam results
	     List<UserResults> examResults = userResultsRepository.findByUser_UserId(userId);

	     // Calculate total exam score
	     int totalScore = examResults.stream()
	             .mapToInt(UserResults::getExamScore)
	             .sum();

	     // Fetch available tests
	     List<AvailableTestsDto> availableTests = getAllAvailableTests();

	     // Construct UserDashBoardDto
	     UserDashBoardDto userDashBoardDto = new UserDashBoardDto();
	     userDashBoardDto.setUserId(userId);
	     userDashBoardDto.setExamScore(totalScore);
	     userDashBoardDto.setExamDate(LocalDate.now()); // You can set an appropriate date
	     userDashBoardDto.setAvailableTestsDtolist(availableTests);

	     return userDashBoardDto;
	 }

	 private List<AvailableTestsDto> getAllAvailableTests() {
	     List<Test> tests = testRepository.findAll();
	     return tests.stream()
	             .map(test -> new AvailableTestsDto(test.getTestId(), test.getCourseType()))
	             .collect(Collectors.toList());
	 }
	
	
		

		@Override
		public List<UserResults> getUserResults(int userId) {
			// TODO Auto-generated method stub
			return null;
		}

}
