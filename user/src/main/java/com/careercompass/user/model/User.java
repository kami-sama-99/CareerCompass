package com.careercompass.user.model;

import java.util.Date;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.PropertyName;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import lombok.Data;

@Data
public class User {
    @DocumentId
    private String userId;

    private String firstName;
    private String lastName;
    private String email;
    private String dreamJob;
    private String authProvider;

    @PropertyName("socialLinks")
    private SocialLinks socialLinks;

    @Data
    public static class SocialLinks {
        private String linkedin;
        private String github;
    }

    @ServerTimestamp
    private Date createdAt;

    @ServerTimestamp
    private Date updatedAt;
}
