package com.careercompass.user.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.careercompass.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {

    private static final String COLLECTION_NAME = "users";

    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    // Save or update user
    public User save(User user) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        ApiFuture<WriteResult> future = db.collection(COLLECTION_NAME)
                .document(user.getUserId())
                .set(user);
        future.get();
        return user;
    }

    // Get user by ID
    public User findById(String userId) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(userId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(User.class);
        } else {
            return null;
        }
    }

    // Update user
    public User update(String userId, User updatedUser) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        ApiFuture<WriteResult> future = db.collection(COLLECTION_NAME)
                .document(userId)
                .set(updatedUser, SetOptions.merge());
        future.get(); // wait for completion
        return updatedUser;
    }

    // Delete user
    public void delete(String userId) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        ApiFuture<WriteResult> future = db.collection(COLLECTION_NAME)
                .document(userId)
                .delete();
        future.get();
    }
}

