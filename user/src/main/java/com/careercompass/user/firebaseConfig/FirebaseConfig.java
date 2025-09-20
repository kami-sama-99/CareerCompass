package com.careercompass.user.firebaseConfig;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.service-account-key-path}")  // Actually contains JSON content now
    private String serviceAccountJson;

    @PostConstruct
    public void init() throws IOException {
        InputStream serviceAccount;

        if(serviceAccountJson.trim().startsWith("{")) {
            // Treat as JSON string
            serviceAccount = new ByteArrayInputStream(serviceAccountJson.getBytes());
        } else if(serviceAccountJson.startsWith("classpath:")) {
            String path = serviceAccountJson.replace("classpath:", "");
            serviceAccount = this.getClass().getClassLoader().getResourceAsStream(path);
        } else {
            // For local file path (optional)
            serviceAccount = new FileInputStream(serviceAccountJson);
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("career-compass-e2f8b.appspot.com")
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
}
