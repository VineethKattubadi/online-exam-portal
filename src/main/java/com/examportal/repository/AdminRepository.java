package com.examportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examportal.entity.Admin;
import com.examportal.entity.User;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
	Admin findByAdminMail(String adminMail);
	

}
