package com.examportal.serviceimpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.examportal.entity.Questions;
import com.examportal.entity.Test;
import com.examportal.iservice.QuestionsService;
import com.examportal.repository.QuestionsRepository;
@Service
public class QuestionServiceImpl  implements QuestionsService{

	 @Autowired
	    private QuestionsRepository questionsRepository;


		
	

		    @Override
		    public List<Questions> getAllQuestions(int questionId, String question, String optionA, String optionB, String optionC, String optionD) {
		        // You can build a dynamic query based on the provided parameters
		        // For simplicity, I'll assume you want to include all parameters if they are not null or empty

		        ExampleMatcher matcher = ExampleMatcher.matching()
		                .withMatcher("questionId",ExampleMatcher.GenericPropertyMatchers.exact())
		                .withMatcher("question", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
		                .withMatcher("optionA", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
		                .withMatcher("optionB", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
		                .withMatcher("optionC", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
		                .withMatcher("optionD", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		        Questions exampleQuestion = new Questions();
		        exampleQuestion.setQuestionId(questionId);
		        exampleQuestion.setQuestion(question);
		        exampleQuestion.setOptionA(optionA);
		        exampleQuestion.setOptionB(optionB);
		        exampleQuestion.setOptionC(optionC);
		        exampleQuestion.setOptionD(optionD);

		        Example<Questions> example = Example.of(exampleQuestion, matcher);

		        return questionsRepository.findAll(example);
		    }





			@Override
			public Questions addQuestion(Questions questions) {
				// TODO Auto-generated method stub
				return null;
			}





			@Override
			public Questions updateQuestion(Questions questions) {
				// TODO Auto-generated method stub
				return null;
			}





			@Override
			public Set<Questions> getQuestions() {
				// TODO Auto-generated method stub
				return null;
			}





			@Override
			public Questions getQuestion(int quesId) {
				// TODO Auto-generated method stub
				return null;
			}





			@Override
			public Set<Questions> getQuestionsofTest(Test testManagement) {
				// TODO Auto-generated method stub
				return null;
			}
	

}
