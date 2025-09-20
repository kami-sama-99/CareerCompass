package com.careercompass.exam_service.controller;

import com.careercompass.exam_service.model.ExamResponse;
import com.careercompass.exam_service.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @GetMapping("/recommended")
    public ResponseEntity<List<ExamResponse>> getRecommendedExams() {
        return ResponseEntity.ok(examService.getRecommendedExams());
    }
}
