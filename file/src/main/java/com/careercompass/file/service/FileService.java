package com.careercompass.file.service;

import com.careercompass.file.client.ResumeParserClient;
import com.careercompass.file.model.FileEntity;
import com.careercompass.file.model.FileMetadata;
import com.careercompass.file.model.FileUploadResponse;
import com.careercompass.file.model.ParsedResume;
import com.careercompass.file.repository.FileRepository;
import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final ResumeParserClient resumeParserClient;

    public FileMetadata uploadFile(MultipartFile file, String userId) throws IOException {
        // Create unique file name for Firebase
        if (!file.isEmpty()) System.out.println("File received");
        String originalName = file.getOriginalFilename();
        String fileName = userId + "_" + (originalName != null ? originalName : "resume") + "_Resume";

        // ✅ Upload file to Firebase Storage
        Blob blob = StorageClient.getInstance()
                .bucket()
                .create(fileName, file.getInputStream(), file.getContentType());

        // ✅ Generate signed download URL (valid for 1 hour)
        String downloadUrl = blob.signUrl(1, TimeUnit.HOURS).toString();

        // ✅ Save metadata in Firestore
        FileEntity entity = new FileEntity(
                userId,
                fileName,
                downloadUrl,
                file.getSize(),
                file.getContentType(),
                new Date()
        );
        fileRepository.save(entity);

        // ✅ Parse resume using your Python API
//        ParsedResume parsedResume = resumeParserClient.getParsedResume(file);

        // ✅ Build metadata DTO
        FileMetadata fileMetadata = new FileMetadata(
                entity.getFileName(),
                entity.getDownloadUrl(),
                entity.getSize()
        );

        System.out.println("Sending response: " + fileMetadata);

        return fileMetadata;

        // ✅ Return combined response (metadata + parsed resume)
//        return new FileUploadResponse(fileMetadata, parsedResume);
    }


    public FileMetadata getFileMetadata(String fileId) throws Exception {
        FileEntity entity = fileRepository.findById(fileId);
        if (entity == null) return null;
        return new FileMetadata(entity.getFileName(), entity.getDownloadUrl(), entity.getSize());
    }
}
