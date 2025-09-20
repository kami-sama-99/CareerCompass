package com.careercompass.job_search.service;

import com.careercompass.job_search.client.JobSearchClient;
import com.careercompass.job_search.model.JobSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSearchService {

    private final JobSearchClient jobSearchClient;

    public List<JobSearchResponse> getMatchingColleges(
            String query
    ) {
        ResponseEntity<String> result = jobSearchClient.getColleges(query);
    }
}
