package com.examportal.iservice;

import java.util.List;
import java.util.Set;

import com.examportal.entity.Questions;
import com.examportal.entity.Test;

public interface QuestionsService {
	public Questions addQuestion(Questions questions);
	public Questions updateQuestion(Questions questions);
	public Set<Questions> getQuestions();
	public Questions getQuestion(int quesId);
	public Set<Questions> getQuestionsofTest(Test testManagement);
	public List<Questions> getAllQuestions(int questionId, String question, String optionA, String optionB, String optionC, String optionD);
	

}
