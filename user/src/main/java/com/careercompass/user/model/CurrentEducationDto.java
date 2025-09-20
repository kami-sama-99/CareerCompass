package com.careercompass.user.model;

import lombok.Data;

import java.util.List;

@Data
public class CurrentEducationDto {
    private String gradeLevel;
    private String schoolName;
    private String board;
    private Double percentage;
    private List<String> subjects;
    private Integer expectedGraduationYear;
}
