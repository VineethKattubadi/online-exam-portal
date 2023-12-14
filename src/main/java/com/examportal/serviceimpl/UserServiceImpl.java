package com.examportal.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examportal.dto.AnswerDto;
import com.examportal.dto.QuestionDto;
import com.examportal.dto.ResultDto;
import com.examportal.dto.TestDto;
import com.examportal.dto.TestEnrollDto;
import com.examportal.dto.UserDto;
import com.examportal.dto.UserLoginDto;
import com.examportal.dto.UserRegistrationDto;
import com.examportal.entity.Questions;
import com.examportal.entity.Test;
//import com.examportal.dto.UserRegistrationDto;
import com.examportal.entity.User;
import com.examportal.entity.UserResults;
import com.examportal.exception.InvalidCredentialsException;
import com.examportal.exception.UnAcceptableInformationException;
import com.examportal.exception.UserNotFoundException;
import com.examportal.exception.UserUpdateException;
import com.examportal.iservice.UserService;
import com.examportal.repository.TestRepository;
import com.examportal.repository.UserRepository;
import com.examportal.repository.UserResultsRepository;
import com.examportal.util.AppConstants;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Autowired
	private TestRepository testRepository;
	
	@Autowired
    private UserResultsRepository userResultsRepository;

	@Transactional(readOnly = true)
	public List<User> getAllUser() throws InvalidCredentialsException {

		// return productRepository.findAll();
		List<User> ulist = userRepository.findAll();
		if (!ulist.isEmpty())
			return ulist;
		throw new InvalidCredentialsException("No  Found");
	}

	@Override
	public User loginuser(String userMail, String userpassword) {
		Optional<User> userOptional = userRepository.findByUserMailIgnoreCase(userMail);

		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (user.getUserPassword().equals(userpassword)) {
				return user;
			} else {
				throw new InvalidCredentialsException("Invalid password");
			}
		} else {
			throw new InvalidCredentialsException("User not found");
		}
	}

	@Override
	public void registerUser(UserRegistrationDto registrationRequest) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// User user =User.userRepository.saveAll();
		User newUser = new User();

		newUser.setUserName(registrationRequest.getUserName());
		newUser.setUserId(registrationRequest.getUserId());
		newUser.setUserMail(registrationRequest.getUserMail());
		newUser.setUserAddress(registrationRequest.getUserAddress());
		newUser.setUserMobileNo(registrationRequest.getUserMobileNo());
		newUser.setUserPassword(registrationRequest.getUserPassword());
		userRepository.save(newUser);

	}
	
	
	
	 
	    
	    


//	@Override
//	public String editProfile(String userMail) {
//		Optional<User> userOptional = userRepository.findByUserMail(userMail);
//		if(userOptional.isPresent()) {
//			return convertToDto(userOptional.get());
//		}
//		throw new UserNotFoundException("User not found");
//		
//	}
//	private UserLoginDto convertToDto(User user) {
//		UserLoginDto userLoginDto = new UserLoginDto();
//		
//		
//		return userLoginDto;
	@Override
	public User editProfile(UserDto userDto) {
		if (userDto.getUserMail() != null) {
			Optional<User> optionalUser = userRepository.findByUserMailIgnoreCase(userDto.getUserMail());

			if (optionalUser.isPresent()) {
				User editUser = optionalUser.get();

				if (userDto.getUserName() != null && userDto.getUserName().matches(AppConstants.USER_NAME_REGEX)) {
					editUser.setUserName(userDto.getUserName());
				} else if (userDto.getUserMail() != null) {
					throw new UserUpdateException(AppConstants.INVALID_USER_NAME);
				}

				if (userDto.getUserMobileNo() != 0
						&& Long.toString(userDto.getUserMobileNo()).matches(AppConstants.MOBILE_NUMBER_REGEX)) {
					editUser.setUserMobileNo(userDto.getUserMobileNo());
				} else if (userDto.getUserMobileNo() != 0) {
					throw new UserUpdateException(AppConstants.INVALID_MOBILE_NUMBER);
				}

				// Set other fields based on your requirements

				return userRepository.save(editUser);
			} else {
				throw new UserNotFoundException(
						AppConstants.USER_NOT_FOUND.replace("User not found for email: %s", userDto.getUserMail()));
			}
		} else {
			throw new UnAcceptableInformationException(AppConstants.USER_UPDATE_EXCEPTION);
		}
	}
	
	
	
	
	public void submitUserAnswers(int userId, int testId, Map<Integer, String> userSelectedOptions) {
	    Optional<User> opUser = userRepository.findById(userId);

	    if (opUser.isPresent()) {
	        User user = opUser.get();

	        boolean isEnrolled = user.getEnrolledTests().stream().anyMatch(test -> test.getTestId() == testId);

	        if (isEnrolled) {
	            Test test = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));

	            // Fetch user results or create new results if not exists
	            UserResults userResults = userResultsRepository.findByUserAndTest(user, test)
	                    .orElseGet(() -> {
	                        UserResults newUserResults = new UserResults();
	                        newUserResults.setUser(user);
	                        newUserResults.setTest(test);
	                        newUserResults.setExamDate(LocalDate.now());
	                        return newUserResults;
	                    });

	            // Update user selected options
	            userResults.setUserSelectedOptions(userSelectedOptions);

	            // Save the user results
	            userResultsRepository.save(userResults);
	        } else {
	            throw new RuntimeException("User is not enrolled in the specified test");
	        }
	    } else {
	        throw new RuntimeException("User not found");
	    }
	}


//	public List<TestDto> getAllAvailableTests() {
//		List<Test> availableTests = testRepository.findAll();
//		return convertToTestDto(availableTests);
//	}

	
	public String enrollUserInTest(String userMail, int testId) {
		User user = userRepository.findByUserMailIgnoreCase(userMail).orElse(null);
		Test test = testRepository.findById(testId).orElse(null);

		if (user != null && test != null && !user.getEnrolledTests().contains(test)) {
			user.getEnrolledTests().add(test);
			userRepository.save(user);
			return "Enrolled to " + test.getCourseType() + " test successfully";
		}
		return null;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void enrollUserInTest(int userId, int testId) {
		User user = userRepository.findById(userId).orElse(null);
		Test test = testRepository.findById(testId).orElse(null);

		if (user != null && test != null && !user.getEnrolledTests().contains(test)) {
			user.getEnrolledTests().add(test);
			userRepository.save(user);
		}
	}

//	@Override
//	public String enrolledUserInTest(TestEnrollDto testEnrollDto) {
//		return null;
//	}

	public String startTest(int userId, int testId) {
		Optional<User> opUser = userRepository.findById(userId);
		if (opUser.isPresent()) {
			User user = opUser.get();
			if (user.getEnrolledTests().stream().anyMatch(a -> a.getTestId() == testId)) {
				return "start the test";
				
			} else {
				return "please enroll for test";
			}
		}
		return "user not found";
	}
	
    public List<Questions> getAllQuestions(int questionId, String question, String optionA, String optionB, String optionC, String optionD){
    	
		return null;
    	
    }

	@Override
	public String enrolledUserInTest(TestEnrollDto testEnrollDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
}





