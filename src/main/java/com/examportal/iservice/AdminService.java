package com.examportal.iservice;

import java.util.List;

import com.examportal.dto.AllUsersDto;
import com.examportal.dto.ResultDto;
import com.examportal.dto.UserDto;
import com.examportal.entity.Admin;
import com.examportal.entity.User;

public interface AdminService {
	public List<ResultDto> getUserResults(int userId, int testId);
	public Admin login(String adminMail,String adminPassword);

}
