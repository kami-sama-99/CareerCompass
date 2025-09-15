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

    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    public void save(FileEntity file) {
        Firestore db = getFirestore();
        db.collection("files").document(file.getFileId()).set(file);
    }

    public FileEntity findById(String fileId) throws InterruptedException, ExecutionException {
        Firestore db = getFirestore();
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
