package com.examportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examportal.entity.Test;
import com.examportal.entity.User;
import com.examportal.entity.UserResults;
@Repository
public interface UserResultsRepository extends JpaRepository<UserResults,Integer> {

	Optional<UserResults> findByUserAndTest(User user, Test test);

}
