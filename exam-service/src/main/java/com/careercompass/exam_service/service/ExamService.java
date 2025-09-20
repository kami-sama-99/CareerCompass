package com.careercompass.exam_service.service;

import com.careercompass.exam_service.client.ExamClient;
import com.careercompass.exam_service.model.Exam;
import com.careercompass.exam_service.model.ExamResponse;
import com.careercompass.exam_service.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamClient examClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ExamResponse> getRecommendedExams() {
        System.out.println("Request Received");
        List<Exam> examList = mapResponseToExamList(examClient.getExams("engineering").getBody());
        List<ExamResponse> examResponseList = new ArrayList<>();
        for (Exam exam : examList) {
            exam.setId(UUID.randomUUID().toString());
            examRepository.save(exam);
            examResponseList.add(mapExamToExamResponse(exam));
        }
        System.out.println("Response sent: " + examResponseList);
        return examResponseList;
    }

    private ExamResponse mapExamToExamResponse(Exam exam) {
        ExamResponse response = new ExamResponse();
        response.setId(exam.getId());
        response.setName(exam.getName());
        response.setRegistrationDate(exam.getRegistrationDate());
        response.setDeadlineDate(exam.getDeadlineDate());
        response.setRegistrationPortalLink(exam.getRegistrationPortalLink());
        response.setEligibility(exam.getEligibility());
        return response;
    }


    private List<Exam> mapResponseToExamList(String rawJson) {
        try {
            return objectMapper.readValue(rawJson, new TypeReference<List<Exam>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse exam JSON", e);
        }
    }
}
