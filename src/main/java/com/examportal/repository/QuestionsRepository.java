package com.examportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examportal.dto.QuestionDto;
import com.examportal.entity.Questions;
import com.examportal.entity.Test;
@Repository
public interface QuestionsRepository extends JpaRepository<Questions,Integer>{
	Optional<Questions> findByQuestionId(int questionId);


	// In QuestionsRepository
	List<Questions> findByTest(Test test);

}
