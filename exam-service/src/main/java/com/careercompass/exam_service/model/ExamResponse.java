package com.careercompass.exam_service.model;

import lombok.Data;

@Data
public class ExamResponse {
    private String id;
    private String name;
    private String registrationDate;
    private String deadlineDate;
    private String registrationPortalLink;
    private String eligibility;
}
