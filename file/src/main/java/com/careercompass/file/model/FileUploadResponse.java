package com.careercompass.file.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadResponse {
    private FileMetadata fileMetadata;
    private ParsedResume parsedResume;
}
