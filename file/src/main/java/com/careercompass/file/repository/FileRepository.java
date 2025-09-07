package com.careercompass.file.repository;

import com.careercompass.file.model.FileEntity;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class FileRepository {

    private final Firestore db = FirestoreClient.getFirestore();

    public void save(FileEntity file) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("fileId", file.getFileId());
        docData.put("fileName", file.getFileName());
        docData.put("downloadUrl", file.getDownloadUrl());
        docData.put("size", file.getSize());
        docData.put("contentType", file.getContentType());
        docData.put("uploadedAt", file.getUploadedAt() != null ? file.getUploadedAt() : new Date());

        db.collection("files").document(file.getFileId()).set(docData);
    }

    public FileEntity findById(String fileId) throws InterruptedException, ExecutionException {
        DocumentSnapshot snapshot = db.collection("files").document(fileId).get().get();
        if (!snapshot.exists()) return null;

        FileEntity file = new FileEntity();
        file.setFileId(snapshot.getString("fileId"));
        file.setFileName(snapshot.getString("fileName"));
        file.setDownloadUrl(snapshot.getString("downloadUrl"));
        file.setSize(snapshot.getLong("size"));
        file.setContentType(snapshot.getString("contentType"));
        file.setUploadedAt(snapshot.getDate("uploadedAt"));

        return file;
    }
}
