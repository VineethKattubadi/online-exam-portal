package com.examportal.dto;

import java.time.LocalDate;
import java.util.List;

import com.examportal.entity.UserResults;

public class UserDashBoardDto {
		private int userId;
		private int examScore;
		private LocalDate examDate;
		private List<AvailableTestsDto> availableTestsDtolist ;
		public UserDashBoardDto() {
			super();
		}
		public UserDashBoardDto(int userId, int examScore, LocalDate examDate,
				List<AvailableTestsDto> availableTestsDtolist) {
			super();
			this.userId = userId;
			this.examScore = examScore;
			this.examDate = examDate;
			this.availableTestsDtolist = availableTestsDtolist;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getExamScore() {
			return examScore;
		}
		public void setExamScore(int examScore) {
			this.examScore = examScore;
		}
		public LocalDate getExamDate() {
			return examDate;
		}
		public void setExamDate(LocalDate examDate) {
			this.examDate = examDate;
		}
		public List<AvailableTestsDto> getAvailableTestsDtolist() {
			return availableTestsDtolist;
		}
		public void setAvailableTestsDtolist(List<AvailableTestsDto> availableTestsDtolist) {
			this.availableTestsDtolist = availableTestsDtolist;
		}
		
		
		
	 
	   
}
