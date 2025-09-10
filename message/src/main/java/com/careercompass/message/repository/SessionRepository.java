package com.careercompass.message.repository;

import com.careercompass.message.model.Session;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static com.google.firebase.cloud.FirestoreClient.getFirestore;

@Repository
public class SessionRepository {
    private static final String SESSION = "sessions";
    private static final String USER = "users";

    public Session findById(String userId, String sessionId) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        DocumentReference userRef = db.collection(USER).document(userId);
        DocumentReference sessionRef = userRef.collection(SESSION).document(sessionId);
        ApiFuture<DocumentSnapshot> future = sessionRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(Session.class);
        } else {
            return null;
        }
    }

    public String save(String userId, Session session) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();

        // Generate a sessionId
        String sessionId = UUID.randomUUID().toString();
        session.setSessionId(sessionId);

        // Save session with that ID
        ApiFuture<WriteResult> future = db.collection(USER)
                .document(userId)
                .collection(SESSION)
                .document(sessionId)   // use the generated ID here
                .set(session);

        future.get();
        return sessionId;
    }
}
