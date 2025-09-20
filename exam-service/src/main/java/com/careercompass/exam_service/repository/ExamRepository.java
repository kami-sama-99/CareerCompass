package com.careercompass.exam_service.repository;

import com.careercompass.exam_service.model.Exam;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExamRepository {

    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
    private final String parentCollection = "ai";
    private final String child = "exam";

    public void save(Exam exam) {
        Firestore db = getFirestore();
        db.collection(parentCollection).document().collection(child).document(exam.getId()).set(exam);
    }
}
