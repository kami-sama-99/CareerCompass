package com.careercompass.file.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileMetadata {
    private String fileName;
    private String downloadUrl;
    private long size;
}

