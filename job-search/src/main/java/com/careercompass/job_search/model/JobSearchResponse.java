package com.careercompass.job_search.model;

import lombok.Data;

@Data
public class JobSearchResponse {
    private String jobTitle;
    private String company;
    private String jobDescription;
    private double matchScore;
    private String applyLink;
}
