package com.careercompass.user.model;

import lombok.Data;

import java.util.List;

@Data
public class StudentRequest {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String dob;
    private String location;

    private CurrentEducationDto currentEducation;
    private List<TestScoreDto> testScores;
}
