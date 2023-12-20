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
import com.examportal.iservice.TestService;
import com.examportal.repository.QuestionsRepository;
import com.examportal.repository.TestRepository;
import com.examportal.repository.UserRepository;
import com.examportal.repository.UserResultsRepository;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestRepository testRepository;
	
	@Autowired
	private TestService testService;

	@Autowired
	private QuestionsRepository questionsRepository;
	
	 @Autowired
	    private UserRepository userRepository;
	 
	 @Autowired
	 private UserResultsRepository userResultsRepository;

	// adding test logic by admin (check AdminController for HTTP PUT Requets)

	 public TestDto addTestWithQuestions(TestDto testDto) {
	        try {
	            // Your existing implementation
	            Test test = new Test();
	            test.setTestId(testDto.getTestId());
	            test.setCourseType(testDto.getCourseType());
	            // other mappings...

	            test = testRepository.save(test);

	            for (QuestionDto questionDto : testDto.getQuestions()) {
	                Questions question = new Questions();
	                question.setQuestionId(questionDto.getQuestionId());
	                question.setQuestion(questionDto.getQuestion());
	                question.setOptionA(questionDto.getOptionA());
	                question.setOptionB(questionDto.getOptionB());
	                question.setOptionC(questionDto.getOptionC());
	                question.setOptionD(questionDto.getOptionD());
	                question.setAnswer(questionDto.getAnswer());
	                // other mappings...

	                question.setTest(test);
	                questionsRepository.save(question);
	            }

	            return testDto;
	        } catch (Exception ex) {
	            throw new TestServiceException("An error occurred while adding test with questions.");
	        }
	    }
	
	

// AvailableTests logic for User and Admin.
	 @Transactional(readOnly = true)
	    public List<AvailableTestsDto> getAllTests() {
	        try {
	            List<Test> testList = testRepository.findAll();
	            return convertToAvailableTestsDto(testList);
	        } catch (Exception ex) {
	            throw new TestServiceException("An error occurred while fetching available tests.");
	        }
	    }

	    public List<AvailableTestsDto> convertToAvailableTestsDto(List<Test> testList) {
	        return testList.stream()
	                .map(test -> new AvailableTestsDto(test.getTestId(), test.getCourseType()))
	                .collect(Collectors.toList());
	    }

	// getting the questions with answers based on the testId for admin.
	    public List<QuestionDto> getQuestionsByTestId(int testId) {
	        try {
	            Optional<Test> opTest = testRepository.findById(testId);
	            if (opTest.isPresent()) {
	                List<Questions> questions = opTest.get().getQuestions();
	                return convertToQuestionDto(questions);
	            }
	            throw new TestServiceException("Test not found for ID: " + testId);
	        } catch (Exception ex) {
	            throw new TestServiceException("An error occurred while fetching questions.");
	        }
	    }

	    private List<QuestionDto> convertToQuestionDto(List<Questions> questions) {
	        List<QuestionDto> questionDtos = new ArrayList<>();

	        for (Questions question : questions) {
	            QuestionDto questionDto = new QuestionDto();
	            questionDto.setQuestionId(question.getQuestionId());
	            questionDto.setQuestion(question.getQuestion());
	            questionDto.setOptionA(question.getOptionA());
	            questionDto.setOptionB(question.getOptionB());
	            questionDto.setOptionC(question.getOptionC());
	            questionDto.setOptionD(question.getOptionD());
	            questionDto.setAnswer(question.getAnswer());

	            questionDtos.add(questionDto);
	        }

	        return questionDtos;
	    }
	
	
	
	
	
	//----Method for enrolled user to getall questions------.
	
	 public List<QuestionDto> getQuestionsForEnrolledUser(int userId, int testId) {
	        Optional<User> opUser = userRepository.findById(userId);

	        if (opUser.isPresent()) {
	            User user = opUser.get();

	            boolean isEnrolled = user.getEnrolledTests().stream().anyMatch(a -> a.getTestId() == testId);

	            if (isEnrolled) {
	                Test test = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
	                List<Questions> questions = test.getQuestions();
	                
	                // Convert Questions to QuestionDto
	                List<QuestionDto> questionDtos = questions.stream()
	                        .map(q -> new QuestionDto(q.getQuestionId(), q.getQuestion(), q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD()))
	                        .collect(Collectors.toList());
	                
	                return questionDtos;
	            } else {
	                throw new RuntimeException("User is not enrolled in the specified test");
	            }
	        }

	        throw new RuntimeException("User not found");
	    }
	 
	 
//-----logic for User submitting answers and getting results of a particuar test(check UserController for Http Put request)
	
	 
	 public TestResultDto submitAndCalculateScore(int userId, int testId, Map<Integer, String> userSelectedOptions) {
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

		            // Calculate and update user score
		            int score = calculateScore(test.getQuestions(), userSelectedOptions);
		            userResults.setExamScore(score);

		            // Update user selected options
		            userResults.setUserSelectedOptions(userSelectedOptions);

		            // Save the user results
		            userResultsRepository.save(userResults);

		            // Display right and wrong answers
		            List<ResultDto> resultDtos = displayAnswers(test.getQuestions(), userSelectedOptions);

		            // Calculate total marks
		            int totalQuestions = test.getQuestions().size(); // assuming each question carries 1 mark

		            // Return the result
		            return new TestResultDto(score, totalQuestions, resultDtos);
		        } else {
		            throw new RuntimeException("User is not enrolled in the specified test");
		        }
		    } else {
		        throw new RuntimeException("User not found");
		    }
		}

		public int calculateScore(List<Questions> questions, Map<Integer, String> userSelectedOptions) {
		    int score = 0;
		    for (Questions question : questions) {
		        int questionId = question.getQuestionId();
		        String userSelectedOption = userSelectedOptions.get(questionId);
		        if (question.getAnswer().equals(userSelectedOption)) {
		            // Increase the score for correct answers
		            score++;
		        }
		    }
		    return score;
		}

		public List<ResultDto> displayAnswers(List<Questions> questions, Map<Integer, String> userSelectedOptions) {
		    return questions.stream()
		            .map(question -> {
		                int questionId = question.getQuestionId();
		                String userSelectedOption = userSelectedOptions.get(questionId);
		                boolean isCorrect = question.getAnswer().equals(userSelectedOption);
		                return new ResultDto(questionId, question.getQuestion(),
		                        question.getAnswer(), userSelectedOption, isCorrect);
		            })
		            .collect(Collectors.toList());
		}

		

	 
	 

	}
	
	
	


//	   
