package com.careercompass.user.repository;

import com.careercompass.user.model.Student;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class StudentRepository {

    private static final String COLLECTION_NAME = "students"; // ✅ change collection

    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    // Save or update student
    public Student save(Student student) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        ApiFuture<WriteResult> future = db.collection(COLLECTION_NAME)
                .document(student.getId()) // using Student.id as document ID
                .set(student);
        future.get(); // wait for completion
        return student;
    }

    // Optional: get student by id
    public Student findById(String id) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        return db.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .get()
                .toObject(Student.class);
    }

    // Optional: delete student
    public void delete(String id) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        db.collection(COLLECTION_NAME).document(id).delete().get();
    }
}

