package com.examportal.serviceimpltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.examportal.dto.AvailableTestsDto;
import com.examportal.dto.UserDashBoardDto;
import com.examportal.entity.Test;
import com.examportal.entity.User;
import com.examportal.entity.UserResults;
import com.examportal.repository.TestRepository;
import com.examportal.repository.UserRepository;
import com.examportal.repository.UserResultsRepository;
import com.examportal.serviceimpl.UserResultsServiceImpl;

@SpringBootTest
public class UserResultsServiceImplTest {
 
    @Mock
    private UserRepository userRepository;
 
    @Mock
    private UserResultsRepository userResultsRepository;
 
    @Mock
    private TestRepository testRepository;
 
    @InjectMocks
    private UserResultsServiceImpl userResultsService;
 
    @org.junit.jupiter.api.Test
    public void testGetUserDashBoard() {
        // Mock user
        User user = new User();
        user.setUserId(1);
 
        // Mock exam results
        List<UserResults> examResults = Arrays.asList(
                new UserResults(1, user, LocalDate.now(), 80),
                new UserResults(2, user, LocalDate.now(), 90)
                // Add more example results as needed
        );
 
        // Mock available tests
        List<Test> availableTests = Arrays.asList(
                new Test(1, "Math")
                // Add more example tests as needed
        );
 
        // Mock behavior
        when(userRepository.findById(anyInt())).thenReturn(java.util.Optional.of(user));
        when(userResultsRepository.findByUser_UserId(anyInt())).thenReturn(examResults);
        when(testRepository.findAll()).thenReturn(availableTests);
 
        // Call the method to be tested
        UserDashBoardDto result = userResultsService.getUserDashBoard(user.getUserId());
 
        // Assert the result or check any other conditions
        assertNotNull(result);
        assertEquals(user.getUserId(), result.getUserId());
        assertEquals(170, result.getExamScore()); // Sum of exam scores
        assertEquals(LocalDate.now(), result.getExamDate());
 
        // Assuming there is one available test in the test repository
        assertEquals(1, result.getAvailableTestsDtolist().size());
        AvailableTestsDto availableTest = result.getAvailableTestsDtolist().get(0);
        assertEquals(1, availableTest.getTestId());
        assertEquals("Math", availableTest.getCourseType());
 
        // Ensure that there are no more interactions with the repositories
//        verifyNoMoreInteractions(userRepository, userResultsRepository, testRepository);
    }
}
