package com.examportal.serviceimpltest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.examportal.dto.UserRegistrationDto;
import com.examportal.entity.User;
import com.examportal.exception.InvalidCredentialsException;
import com.examportal.exception.UserNotFoundException;
import com.examportal.repository.UserRepository;
import com.examportal.serviceimpl.UserServiceImpl;

@SpringBootTest
class UserServiceImplTest {
	 @Mock
	    private UserRepository userRepository;

	    @InjectMocks
	    private UserServiceImpl userServiceImpl;

	    @Test
	    void testGetAllUser() {
	        // Arrange
	        List<User> userList = new ArrayList<>();
	        userList.add(new User(1, "John Doe", "john@example.com", "password"));
	        userList.add(new User(2, "Jane Doe", "jane@example.com", "password"));

	        when(userRepository.findAll()).thenReturn(userList);

	        // Act
	        List<User> result = userServiceImpl.getAllUser();

	        // Assert
	        assertNotNull(result);
	        assertEquals(2, result.size());
	    }

	    @Test
	    void testGetAllUserNoUsers() {
	        // Arrange
	        when(userRepository.findAll()).thenReturn(new ArrayList<>());

	        // Act and Assert
	        assertThrows(InvalidCredentialsException.class, () -> userServiceImpl.getAllUser());
	    }

	    @Test
	    void testLoginUserWithValidCredentials() throws InvalidCredentialsException {
	        // Mock data
	        String userMail = "test@example.com";
	        String userPassword = "password123";

	        User mockUser = new User();
	        mockUser.setUserMail(userMail);
	        mockUser.setUserPassword(userPassword);

	        // Mock repository behavior
	        when(userRepository.findByUserMailIgnoreCase(userMail)).thenReturn(Optional.of(mockUser));

	        // Call the service method
	        User resultUser = userServiceImpl.loginuser(userMail, userPassword);

	        // Verify interactions
	        verify(userRepository, times(1)).findByUserMailIgnoreCase(userMail);

	        // Assert the result
	        assertNotNull(resultUser);
	        assertEquals(mockUser, resultUser);
	    }

	    @Test
	    void testLoginUserWithInvalidPassword() {
	        // Mock data
	        String userMail = "test@example.com";
	        String userPassword = "incorrectPassword";

	        User mockUser = new User();
	        mockUser.setUserMail(userMail);
	        mockUser.setUserPassword("password123"); // Correct password

	        // Mock repository behavior
	        when(userRepository.findByUserMailIgnoreCase(userMail)).thenReturn(Optional.of(mockUser));

	        // Call the service method and expect an exception
	        assertThrows(InvalidCredentialsException.class, () -> {
	            userServiceImpl.loginuser(userMail, userPassword);
	        });

	        // Verify interactions
	        verify(userRepository, times(1)).findByUserMailIgnoreCase(userMail);
	    }

	    @Test
	    void testLoginUserWithUnknownUser() {
	        // Mock data
	        String userMail = "nonexistent@example.com";
	        String userPassword = "password123";

	        // Mock repository behavior
	        when(userRepository.findByUserMailIgnoreCase(userMail)).thenReturn(Optional.empty());

	        // Call the service method and expect an exception
	        assertThrows(InvalidCredentialsException.class, () -> {
	            userServiceImpl.loginuser(userMail, userPassword);
	        });

	        // Verify interactions
	        verify(userRepository, times(1)).findByUserMailIgnoreCase(userMail);
	    }
	    
	    @Test
	    void testRegisterUser() {
	        // Mock data
	        UserRegistrationDto registrationRequest = new UserRegistrationDto(
	                "John Doe",
	                100,
	                "john@example.com",
	                1234567890L,
	                "123 Main St",
	                "password123"
	        );

	        // Mock repository behavior
	        when(userRepository.save(any(User.class))).thenReturn(new User());

	        // Call the service method
	        assertDoesNotThrow(() -> userServiceImpl.registerUser(registrationRequest));

	        // Verify interactions
	        verify(userRepository, times(1)).save(any(User.class));
	    }
	    
	    @org.junit.jupiter.api.Test
	    public void testDeleteUser() {
	        // Given
	        Integer userId = 1;
	        User user = new User();
	        user.setUserId(userId);
 
	        // Mocking repository behavior
	        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	        // Optional.of(user) can be replaced with Optional.ofNullable(user) if you want to test for the null case as well
 
	        // When
	        userServiceImpl.deleteUser(userId);
 
	        // Then
	        // Verifying that deleteById method was called with the correct userId
	        verify(userRepository, times(1)).deleteById(userId);
	    }
 
	    @org.junit.jupiter.api.Test
	    public void testDeleteUser_UserNotFound() {
	        // Given
	        Integer userId = 1;
 
	        // Mocking repository behavior
	        when(userRepository.findById(userId)).thenReturn(Optional.empty());
 
	        // When and Then
	        // UserNotFoundException should be thrown
	        // In JUnit 5, you can use assertThrows to verify the exception
	        assertThrows(UserNotFoundException.class, () -> userServiceImpl.deleteUser(userId));
	    }
}