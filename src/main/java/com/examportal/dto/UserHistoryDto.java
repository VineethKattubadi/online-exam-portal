package com.examportal.dto;

import java.time.LocalDate;

public class UserHistoryDto {

    private int testId;
    private LocalDate examDate;
    private int examScore;

    public UserHistoryDto() {
        // Default constructor
    }

    public UserHistoryDto(int testId, LocalDate examDate, int examScore) {
        this.testId = testId;
        this.examDate = examDate;
        this.examScore = examScore;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public int getExamScore() {
        return examScore;
    }

    public void setExamScore(int examScore) {
        this.examScore = examScore;
    }
}