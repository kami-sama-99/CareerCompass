package com.careercompass.user.model;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.PropertyName;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Student {
    @DocumentId
    private String id;

    private String name;
    private String email;
    private String phone;
    private String dob;
    private String location;

    @PropertyName("current education")
    private CurrentEducationDto currentEducation;

    @PropertyName("tests")
    private List<TestScoreDto> testScores;

    @PropertyName("preferences")
    private ApplicationPreferencesDto applicationPreferences;

    private boolean onboardingCompleted;

    @ServerTimestamp
    private Date createdAt;

    @ServerTimestamp
    private Date lastLogin;
}
