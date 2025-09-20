package com.careercompass.user.model;

import com.google.cloud.firestore.annotation.PropertyName;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StudentResponse {
    private String id;

    private String name;
    private String email;
    private String phone;
    private String dob;
    private String location;
    private CurrentEducationDto currentEducation;
    private List<TestScoreDto> testScores;
    private ApplicationPreferencesDto applicationPreferences;

    private boolean onboardingCompleted;
    private Date createdAt;
    private Date lastLogin;
}
