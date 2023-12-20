package com.examportal.iservice;

import java.util.List;

import com.examportal.dto.UserDashBoardDto;
import com.examportal.entity.UserResults;

public interface UserResultsService  {
	public UserDashBoardDto getUserDashBoard(int userId);

	public List<UserResults> getUserResults(int userId);
	
	
	

}
