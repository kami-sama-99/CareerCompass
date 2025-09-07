package com.careercompass.file.firebaseConfig;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.service-account-key-path}")
    private String serviceAccountPath;

    @Value("${firebase.storage-bucket}") // add bucket property
    private String storageBucket;

    @PostConstruct
    public void init() throws IOException {
        InputStream serviceAccount;

        if (serviceAccountPath.startsWith("classpath:")) {
            String path = serviceAccountPath.replace("classpath:", "");
            serviceAccount = this.getClass().getClassLoader().getResourceAsStream(path);
        } else {
            serviceAccount = new FileInputStream(serviceAccountPath);
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(storageBucket) // set the storage bucket here
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
}
