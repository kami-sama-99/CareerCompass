package com.careercompass.file.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    private String fileId;
    private String fileName;
    private String downloadUrl;
    private long size;
    private String contentType;
    private Date uploadedAt;
}

