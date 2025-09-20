package com.careercompass.job_search.controller;

import com.careercompass.job_search.model.JobSearchResponse;
import com.careercompass.job_search.service.JobSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/get-matching-jobs")
@RequiredArgsConstructor
public class JobSearchController {

    private final JobSearchService jobSearchService;

    @GetMapping
    public ResponseEntity<List<JobSearchResponse>> getMatchingJobs(
            @RequestParam String query
    ) {
        return ResponseEntity.ok(jobSearchService.getMatchingColleges(query));
    }
}
