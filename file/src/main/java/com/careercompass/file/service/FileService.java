package com.careercompass.file.service;

import com.careercompass.file.model.FileEntity;
import com.careercompass.file.model.FileMetadata;
import com.careercompass.file.repository.FileRepository;
import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileMetadata uploadFile(MultipartFile file, String userId) throws IOException {
        String fileName = file.getOriginalFilename();
        //gs://
        //career-compass-e2f8b.firebasestorage.app

        // Upload to Firebase Storage
        Blob blob = StorageClient.getInstance()
                .bucket()
                .create(userId + "_" + fileName, file.getInputStream(), file.getContentType());

        String downloadUrl = blob.signUrl(1, TimeUnit.HOURS).toString();
        long size = file.getSize();

        // Save metadata in Firestore via repository
        FileEntity entity = new FileEntity(userId, fileName, downloadUrl, size, file.getContentType(), new Date());
        fileRepository.save(entity);

        return new FileMetadata(fileName, downloadUrl, size);
    }

    public FileMetadata getFileMetadata(String fileId) throws Exception {
        FileEntity entity = fileRepository.findById(fileId);
        if (entity == null) return null;
        return new FileMetadata(entity.getFileName(), entity.getDownloadUrl(), entity.getSize());
    }
}
