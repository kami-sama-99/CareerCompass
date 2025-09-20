package com.careercompass.exam_service.client;

import jakarta.ws.rs.QueryParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/ai/exams")
public interface ExamClient {
    @GetExchange
    public ResponseEntity<String> getExams(
            @RequestParam("field") String field
    );
}
