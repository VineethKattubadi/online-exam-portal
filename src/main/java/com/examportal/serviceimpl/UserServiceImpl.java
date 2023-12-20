
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

import org.springframework.web.bind.annotation.GetMapping;
 
import com.examportal.dto.AnswerDto;

import com.examportal.dto.AvailableTestsDto;

import com.examportal.dto.QuestionDto;

import com.examportal.dto.ResultDto;

import com.examportal.dto.TestDto;

import com.examportal.dto.TestEnrollDto;

import com.examportal.dto.UserDashBoardDto;

import com.examportal.dto.UserDto;

import com.examportal.dto.UserHistoryDto;

import com.examportal.dto.UserLoginDto;

import com.examportal.dto.UserRegistrationDto;

import com.examportal.entity.Questions;

import com.examportal.entity.Test;

//import com.examportal.dto.UserRegistrationDto;

import com.examportal.entity.User;

import com.examportal.entity.UserResults;

import com.examportal.exception.InvalidCredentialsException;

import com.examportal.exception.TestServiceException;

import com.examportal.exception.UnAcceptableInformationException;

import com.examportal.exception.UserNotFoundException;

import com.examportal.exception.UserServiceException;

import com.examportal.exception.UserUpdateException;

import com.examportal.iservice.TestService;

import com.examportal.iservice.UserResultsService;

import com.examportal.iservice.UserService;

import com.examportal.repository.TestRepository;

import com.examportal.repository.UserRepository;

import com.examportal.repository.UserResultsRepository;

import com.examportal.util.AppConstants;
 
@Service

public class UserServiceImpl implements UserService {
 
	private  UserRepository userRepository;
 
	@Autowired

	public UserServiceImpl(UserRepository userRepository) {

		super();

		this.userRepository = userRepository;

	}
 
	@Autowired

	private TestRepository testRepository;

	@Autowired

	private TestService testService;

	@Autowired

    private UserResultsRepository userResultsRepository;
 
	@Transactional(readOnly = true)

	public List<User> getAllUser() throws InvalidCredentialsException {
 
		

		List<User> ulist = userRepository.findAll();

		if (!ulist.isEmpty())

			return ulist;

		throw new InvalidCredentialsException("No  Found");

	}

//Logic for User to Login.

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
 
	

//Logic for the User to Register.

	 @Override
	 public void registerUser(UserRegistrationDto registrationRequest) {

		    // Validate user name
		    if (!registrationRequest.getUserName().matches(AppConstants.USER_NAME_REGEX)) {
		        throw new UserServiceException(AppConstants.INVALID_USER_NAME);
		    }

		    // Validate user ID
		    if (registrationRequest.getUserId() <= 0) {
		        throw new UserServiceException("Invalid user ID");
		    }

		    // Validate email format
		    if (!registrationRequest.getUserMail().matches(AppConstants.EMAIL_REGEX)) {
		        throw new UserServiceException(AppConstants.INVALID_EMAIL);
		    }

		    // Validate address (assuming it cannot be null)
		    if (registrationRequest.getUserAddress() == null) {
		        throw new UserServiceException("User address cannot be null");
		    }

		    // Validate mobile number format
		    String mobileNumberStr = String.valueOf(registrationRequest.getUserMobileNo());
		    if (!mobileNumberStr.matches(AppConstants.MOBILE_NUMBER_REGEX)) {
		        throw new UserServiceException(AppConstants.INVALID_MOBILE_NUMBER);
		    }

		    // Validate password length
		    if (registrationRequest.getUserPassword() == null || registrationRequest.getUserPassword().length() < 5) {
		        throw new UserServiceException(AppConstants.INVALID_PASSWORD);
		    }

		    try {
		        User newUser = new User();
		        newUser.setUserName(registrationRequest.getUserName());
		        newUser.setUserId(registrationRequest.getUserId());
		        newUser.setUserMail(registrationRequest.getUserMail());
		        newUser.setUserAddress(registrationRequest.getUserAddress());
		        newUser.setUserMobileNo(registrationRequest.getUserMobileNo());
		        newUser.setUserPassword(registrationRequest.getUserPassword());

		        userRepository.save(newUser);

		    } catch (Exception ex) {
		        throw new UserServiceException("An error occurred while registering the user.");
		    }
		}



 
 
// ----------Using this logic User is able to Edit his profile(check UserController for HTTP PutRequest)----------.

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
 
 
				return userRepository.save(editUser);

			} else {

				throw new UserNotFoundException(

						AppConstants.USER_NOT_FOUND.replace("User not found for email: %s", userDto.getUserMail()));

			}

		} else {

			throw new UnAcceptableInformationException(AppConstants.USER_UPDATE_EXCEPTION);

		}

	}

 
 
	

//This Logic is used by the User for enrolling to a Test

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
 
	



	public void enrollUserInTest(int userId, int testId) {

		User user = userRepository.findById(userId).orElse(null);

		Test test = testRepository.findById(testId).orElse(null);
 
		if (user != null && test != null && !user.getEnrolledTests().contains(test)) {

			user.getEnrolledTests().add(test);

			userRepository.save(user);

		}

	}

	//-----Logic for User to submit the answers and able to view the Results of a particular Test----.

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
 
 
	@Override

    public String startTest(int userId, int testId) {

        try {

            Optional<User> opUser = userRepository.findById(userId);

            if (opUser.isPresent()) {

                User user = opUser.get();

                if (user.getEnrolledTests().stream().anyMatch(a -> a.getTestId() == testId)) {

                    return "start the test";

                } else {

                    throw new TestServiceException("Please enroll for the test.");

                }

            } else {

                throw new TestServiceException("User not found.");

            }

        } catch (Exception ex) {

            throw new TestServiceException("An error occurred while starting the test.");

        }

    }

 
	public List<User> getAllUsers() {

		return userRepository.findAll();

	}
 
	

    public List<Questions> getAllQuestions(int questionId, String question, String optionA, String optionB, String optionC, String optionD){

		return null;

    }


    @Override

    public void updateUser(int userId, UserDto userDto) {

        User existingUser = userRepository.findById(userId)

                .orElseThrow(() -> new UserNotFoundException("User not found with Id:" + userId));
 
        // Update user details

        existingUser.setUserName(userDto.getUserName());

        existingUser.setUserMail(userDto.getUserMail());

        existingUser.setUserMobileNo(userDto.getUserMobileNo());

        existingUser.setUserAddress(userDto.getUserAddress());
 
        // Save the updated user

        userRepository.save(existingUser);

    }

    @Override

	public void deleteUser(Integer userId) {

	    Optional<User> user = userRepository.findById(userId);

	    if (user.isPresent()) {

	        userRepository.deleteById(userId);

	    } else {

	        throw new UserNotFoundException("User not found with ID: " + userId);

	    }

	}

 
	@Override

	public List<TestDto> getAllAvailableTestsforUser() {

		// TODO Auto-generated method stub

		return null;

	}
 
	@Autowired

    private UserResultsService userResultsService;

	// Assuming you have a UserResultsService

 
    
 
	
 
	
 
}
 
 
 