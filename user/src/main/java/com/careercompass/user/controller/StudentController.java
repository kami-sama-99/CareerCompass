package com.careercompass.user.controller;

import com.careercompass.user.model.StudentRequest;
import com.careercompass.user.model.StudentResponse;
import com.careercompass.user.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/onboard")
    public ResponseEntity<StudentResponse> onboardStudent(
            @RequestBody StudentRequest studentRequest
    ) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(studentService.onboardStudent(studentRequest));
    }
}
