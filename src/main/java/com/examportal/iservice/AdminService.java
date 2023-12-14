package com.examportal.iservice;

import java.util.List;

import com.examportal.dto.ResultDto;

public interface AdminService {
	public List<ResultDto> getUserResults(int userId, int testId);


}
