package com.careercompass.file.controller;


import com.careercompass.file.model.FileMetadata;
import com.careercompass.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final FileService fileService;

    @GetMapping("/{id}")
    public ResponseEntity<FileMetadata> getResume(
            @RequestParam("id") String id
    ) throws Exception {
        return ResponseEntity.ok(fileService.getFileMetadata(id));
    }
}
