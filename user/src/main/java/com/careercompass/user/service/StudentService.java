package com.careercompass.user.service;

import com.careercompass.user.model.Student;
import com.careercompass.user.model.StudentRequest;
import com.careercompass.user.model.StudentResponse;
import com.careercompass.user.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentResponse onboardStudent(
            StudentRequest studentRequest
    ) throws ExecutionException, InterruptedException {
        System.out.println("Request received: " + studentRequest);
        Student student = mapStudentRequestToStudent(studentRequest);
        System.out.println("Entry made: " + student);
        studentRepository.save(student);
        return mapStudentToStudentResponse(student);
    }

    private StudentResponse mapStudentToStudentResponse(Student student) {
        if (student == null) {
            return null;
        }

        StudentResponse response = new StudentResponse();

        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setPhone(student.getPhone());
        response.setDob(student.getDob());
        response.setLocation(student.getLocation());
        response.setCurrentEducation(student.getCurrentEducation());
        response.setTestScores(student.getTestScores());
        response.setApplicationPreferences(student.getApplicationPreferences());
        response.setOnboardingCompleted(student.isOnboardingCompleted());
        response.setCreatedAt(student.getCreatedAt());
        response.setLastLogin(student.getLastLogin());

        return response;
    }

    private Student mapStudentRequestToStudent(StudentRequest request) {
        if (request == null) {
            return null;
        }

        Student student = new Student();

        student.setId(request.getId()); // might be null if generated later
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDob(request.getDob());
        student.setLocation(request.getLocation());

        student.setCurrentEducation(request.getCurrentEducation());
        student.setTestScores(request.getTestScores());

        // Initially application preferences may not be in request
        student.setApplicationPreferences(null);

        // Default values for onboarding
        student.setOnboardingCompleted(false);
        
        return student;
    }
}
