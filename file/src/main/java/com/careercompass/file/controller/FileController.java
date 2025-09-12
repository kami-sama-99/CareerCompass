package com.careercompass.file.controller;

import com.careercompass.file.model.FileMetadata;
import com.careercompass.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileMetadata> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") String userId
            ) throws IOException {
        FileMetadata metadata = fileService.uploadFile(file, userId);
        return ResponseEntity.ok(metadata);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<FileMetadata> getFile(@PathVariable String fileName) throws Exception {
        FileMetadata metadata = fileService.getFileMetadata(fileName);
        if (metadata == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(metadata);
    }
}

