package com.examportal.serviceimpltest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.examportal.dto.AvailableTestsDto;
import com.examportal.dto.QuestionDto;
import com.examportal.dto.ResultDto;
import com.examportal.dto.TestDto;
import com.examportal.dto.TestResultDto;
import com.examportal.entity.Questions;
import com.examportal.entity.Test;
import com.examportal.entity.User;
import com.examportal.entity.UserResults;
import com.examportal.exception.TestServiceException;
import com.examportal.repository.QuestionsRepository;
import com.examportal.repository.TestRepository;
import com.examportal.repository.UserRepository;
import com.examportal.repository.UserResultsRepository;
import com.examportal.serviceimpl.TestServiceImpl;
 
@SpringBootTest
public class TestServiceImplTest {
 
	@Mock
	private TestRepository testRepository;
 
	@Mock
	private UserResultsRepository userResultsRepository;
 
	@Mock
	private UserRepository userRepository;
 
	@Mock
	private QuestionsRepository questionsRepository;
 
	@InjectMocks
	private TestServiceImpl testServiceimpl;
 
	public void testAddTestWithQuestions() {
		// Create a sample TestDto
		TestDto testDto = new TestDto();
 
		// Set properties for testDto
		testDto.setTestProperties(1, // Set the testId as needed
				"Sample Course", // Set the courseType as needed
				Arrays.asList(new QuestionDto(/* Set properties for QuestionDto */),
						new QuestionDto(/* Set properties for another QuestionDto */)
				// Add more questions as needed
				), Collections.emptyList() // No need for AnswerDto in this test
		);
 
		// Mock the behavior of repositories
		when(testRepository.save(any(Test.class))).thenAnswer(invocation -> {
			Test savedTest = invocation.getArgument(0);
			savedTest.setTestId(1); // Assuming a generated test ID
			return savedTest;
		});
 
		// Assuming you have a similar setup for questionsRepository
 
		try {
			// Call the method to be tested
			TestDto result = testServiceimpl.addTestWithQuestions(testDto);
 
			// Assert the result or check any other conditions
			assertNotNull(result);
			assertEquals(1, result.getTestId());
			// Add more assertions as needed
 
			// Verify that the repositories' save methods were called
			verify(testRepository, times(1)).save(any(Test.class));
			verify(questionsRepository, times(testDto.getQuestions().size())).save(any(Questions.class));
 
			// Ensure that there are no more interactions with the repositories
			verifyNoMoreInteractions(testRepository, questionsRepository);
 
		} catch (TestServiceException e) {
			// If TestServiceException is unexpectedly thrown, fail the test
			fail("TestServiceException not expected", e);
		}
	}
 
	@org.junit.jupiter.api.Test
	public void testGetAllTests() {
		// Mocking the behavior of testRepository.findAll()
		List<Test> mockTestList = Arrays.asList(new Test(1, "Course 1"), new Test(2, "Course 2")
		// Add more test objects as needed
		);
		when(testRepository.findAll()).thenReturn(mockTestList);
 
		// Call the method to be tested
		List<AvailableTestsDto> result = testServiceimpl.getAllTests();
 
		// Verify that testRepository.findAll() was called
		verify(testRepository, times(1)).findAll();
 
		// Assert the result or check any other conditions
		assertNotNull(result);
		assertEquals(2, result.size()); // Assuming you have two mock tests
 
		// Add more assertions as needed for the conversion logic
 
		// Ensure that there are no more interactions with the repositories
		verifyNoMoreInteractions(testRepository);
	}
 
	@org.junit.jupiter.api.Test
	public void testGetQuestionsByTestId() {
		// Mocking the behavior of testRepository.findById()
		int testId = 1;
		Test mockTest = Mockito.mock(Test.class); // Correct way to create a mock
		when(testRepository.findById(testId)).thenReturn(Optional.of(mockTest));
 
		// Mocking the behavior of test.getQuestions()
		List<Questions> mockQuestions = Arrays.asList(new Questions(1, "Question 1", "A", "B", "C", "D", "A", mockTest),
				new Questions(2, "Question 2", "A", "B", "C", "D", "B", mockTest)
		// Add more questions as needed
		);
		when(mockTest.getQuestions()).thenReturn(mockQuestions);
 
		// Call the method to be tested
		List<QuestionDto> result = testServiceimpl.getQuestionsByTestId(testId);
 
		// Verify that testRepository.findById() was called
		verify(testRepository, times(1)).findById(testId);
 
		// Assert the result or check any other conditions
		assertNotNull(result);
		assertEquals(2, result.size()); // Assuming you have two mock questions
 
		// Add more assertions as needed for the conversion logic
 
		// Ensure that there are no more interactions with the repository
		verifyNoMoreInteractions(testRepository);
	}
 
	@org.junit.jupiter.api.Test
	public void testGetQuestionsForEnrolledUser() {
		// Mocking the behavior of userRepository.findById()
		int userId = 1;
		User mockUser = new User(userId, "testuser", "testmail@example.com", 1234567890L, "Test Address");
		when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
 
		// Mocking the behavior of user.getEnrolledTests()
		int testId = 1;
		Test mockTest = Mockito.mock(Test.class);
		when(mockTest.getTestId()).thenReturn(testId);
		mockUser.setEnrolledTests(Arrays.asList(mockTest));
 
		// Mocking the behavior of testRepository.findById()
		when(testRepository.findById(testId)).thenReturn(Optional.of(mockTest));
 
		// Mocking the behavior of test.getQuestions()
		List<Questions> mockQuestions = Arrays.asList(new Questions(1, "Question 1", "A", "B", "C", "D", "A", mockTest),
				new Questions(2, "Question 2", "A", "B", "C", "D", "B", mockTest)
		// Add more questions as needed
		);
		Mockito.doReturn(mockQuestions).when(mockTest).getQuestions();
 
		// Call the method to be tested
		List<QuestionDto> result = testServiceimpl.getQuestionsForEnrolledUser(userId, testId);
 
		// Verify that userRepository.findById() was called
		verify(userRepository, times(1)).findById(userId);
 
		// Verify that testRepository.findById() was called
		verify(testRepository, times(1)).findById(testId);
 
		// Assert the result or check any other conditions
		assertNotNull(result);
		assertEquals(2, result.size()); // Assuming you have two mock questions
 
		// Add more assertions as needed for the conversion logic
 
		// Ensure that there are no more interactions with the repositories
		verifyNoMoreInteractions(userRepository, testRepository);
	}
 
	public void testSubmitAndCalculateScore() {
		// Mocking the behavior of userRepository.findById()
		int userId = 1;
		User mockUser = new User(userId, "testuser", "testmail@example.com", 1234567890L, "Test Address");
		when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
 
		// Mocking the behavior of user.getEnrolledTests()
		int testId = 1;
		Test mockTest = Mockito.mock(Test.class);
		when(mockTest.getTestId()).thenReturn(testId);
		mockUser.setEnrolledTests(List.of(mockTest));
 
		// Mocking the behavior of testRepository.findById()
		when(testRepository.findById(testId)).thenReturn(Optional.of(mockTest));
 
		// Mocking the behavior of userResultsRepository.findByUserAndTest()
		UserResults mockUserResults = Mockito.mock(UserResults.class);
		when(userResultsRepository.findByUserAndTest(any(), any())).thenReturn(Optional.of(mockUserResults));
 
		// Mocking the behavior of calculateScore and displayAnswers methods
		when(testServiceimpl.calculateScore(anyList(), anyMap())).thenReturn(2); // Assuming 2 correct answers
		when(testServiceimpl.displayAnswers(anyList(), anyMap())).thenReturn(
				List.of(new ResultDto(1, "Question 1", "A", "B", false), new ResultDto(2, "Question 2", "C", "C", true)
				// Add more result objects as needed
				));
 
		// Mocking the behavior of userResultsRepository.save()
		when(userResultsRepository.save(any())).thenReturn(mockUserResults);
 
		// Prepare user selected options
		Map<Integer, String> userSelectedOptions = new HashMap<>();
		userSelectedOptions.put(1, "B");
		userSelectedOptions.put(2, "C");
 
		// Call the method to be tested
		TestResultDto result = testServiceimpl.submitAndCalculateScore(userId, testId, userSelectedOptions);
 
		// Verify that userRepository.findById() was called
		verify(userRepository, times(1)).findById(userId);
 
		// Verify that testRepository.findById() was called
		verify(testRepository, times(1)).findById(testId);
 
		// Verify that userResultsRepository.findByUserAndTest() was called
		verify(userResultsRepository, times(1)).findByUserAndTest(mockUser, mockTest);
 
		// Verify that calculateScore and displayAnswers methods were called
		verify(testServiceimpl, times(1)).calculateScore(anyList(), anyMap());
		verify(testServiceimpl, times(1)).displayAnswers(anyList(), anyMap());
 
		// Verify that userResultsRepository.save() was called
		verify(userResultsRepository, times(1)).save(any(UserResults.class));
 
		// Ensure that there are no more interactions with the repositories
		verifyNoMoreInteractions(userRepository, testRepository, userResultsRepository);
 
		// Ensure that there are no more interactions with the service
		verifyNoMoreInteractions(testServiceimpl);
 
		// Assert the result or check any other conditions
		assertNotNull(result);
		assertEquals(2, result.getScore()); // Assuming 2 correct answers
		assertEquals(2, result.getTotalQuestions()); // Assuming 2 questions
		assertEquals(2, result.getResultDtos().size()); // Assuming 2 result objects
	}
}
