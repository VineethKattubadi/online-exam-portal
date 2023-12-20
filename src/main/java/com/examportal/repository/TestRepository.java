package com.examportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examportal.entity.Test;
import com.examportal.entity.User;
import com.examportal.entity.UserResults;
@Repository
public interface TestRepository extends JpaRepository<Test,Integer> {
	
	List<Test> findByUserResults(UserResults userResults);
}
