package com.examportal.serviceimpltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;

import com.examportal.entity.Questions;
import com.examportal.repository.QuestionsRepository;
import com.examportal.serviceimpl.QuestionServiceImpl;

@SpringBootTest
public class QuestionServiceImplTest {
 
    @Mock
    private QuestionsRepository questionsRepository;
 
    @InjectMocks
    private QuestionServiceImpl questionService;
 
    @Test
    public void testGetAllQuestions() {
        // Mocking the behavior of questionsRepository.findAll()
 
        // Create example input values
        int questionId = 1;
        String question = "Sample question";
        String optionA = "Option A";
        String optionB = "Option B";
        String optionC = "Option C";
        String optionD = "Option D";
 
        // Create an example question
        Questions exampleQuestion = new Questions();
        exampleQuestion.setQuestionId(questionId);
        exampleQuestion.setQuestion(question);
        exampleQuestion.setOptionA(optionA);
        exampleQuestion.setOptionB(optionB);
        exampleQuestion.setOptionC(optionC);
        exampleQuestion.setOptionD(optionD);
 
        // Create an example matcher
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("questionId", GenericPropertyMatchers.exact())
                .withMatcher("question", GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("optionA", GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("optionB", GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("optionC", GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("optionD", GenericPropertyMatchers.contains().ignoreCase());
 
        Example.of(exampleQuestion, matcher);
 
        // Create a list of example questions
        List<Questions> exampleQuestions = Arrays.asList(
                new Questions(1, "Sample question 1", "A1", "B1", "C1", "D1", "A", null),
                new Questions(2, "Sample question 2", "A2", "B2", "C2", "D2", "B", null)
                // Add more example questions as needed
        );
 
        // Mock the behavior of questionsRepository.findAll() to return the exampleQuestions list
        when(questionsRepository.findAll(Mockito.<Example<Questions>>any())).thenReturn(exampleQuestions);
 
        // Call the method to be tested
        List<Questions> result = questionService.getAllQuestions(
                questionId, question, optionA, optionB, optionC, optionD);
 
        // Verify that questionsRepository.findAll() was called with any Example
        verify(questionsRepository, times(1)).findAll(Mockito.<Example<Questions>>any());
 
        // Assert the result or check any other conditions
        assertNotNull(result);
        assertEquals(2, result.size()); // Assuming you have two example questions
 
        // Add more assertions as needed for the actual logic in QuestionServiceImpl
 
        // Ensure that there are no more interactions with the repository
        verifyNoMoreInteractions(questionsRepository);
    }
}
 