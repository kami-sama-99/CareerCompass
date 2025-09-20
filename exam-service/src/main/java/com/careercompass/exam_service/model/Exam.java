package com.careercompass.exam_service.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;

@Data
public class Exam {
    @DocumentId
    private String id;
    private String name;
    private String registrationDate;
    private String deadlineDate;
    private String registrationPortalLink;
    private String eligibility;
}
