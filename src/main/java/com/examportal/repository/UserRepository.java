package com.examportal.repository;

import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.examportal.entity.Questions;
import com.examportal.entity.Test;
import com.examportal.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	Optional<User> findByUserMailIgnoreCase(String userMail);
	Optional<User> findByUserName(String userName);
	Optional<User> getByUserName(String userName);
	List<Questions> findByTest(Test test);
	

}
