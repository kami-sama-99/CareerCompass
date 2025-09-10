package com.careercompass.message.repository;

import com.careercompass.message.model.Message;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.google.firebase.cloud.FirestoreClient.getFirestore;

@Repository
public class MessageRepository {
    private static final String MESSAGE = "messages";
    private static final String SESSION = "sessions";
    private static final String USER = "users";

    public Message save(String userId, String sessionId, Message message) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        ApiFuture<WriteResult> future = db.collection(USER)
                .document(userId)
                .collection(SESSION)
                .document(sessionId).collection(MESSAGE)
                .document().set(message);
        future.get();
        return message;
    }

    public List<Message> findByLimit(int n, String userId, String sessionId) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();

        // Reference to messages subcollection
        CollectionReference messagesRef = db.collection(USER)
                .document(userId)
                .collection(SESSION)
                .document(sessionId)
                .collection(MESSAGE);

        // Query: order by createdAt descending, take N
        ApiFuture<QuerySnapshot> future = messagesRef
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .limit(n)
                .get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Message> messages = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documents) {
            Message msg = doc.toObject(Message.class);
            messages.add(msg);
        }

        return messages;
    }

}
