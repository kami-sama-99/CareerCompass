package com.careercompass.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
public class UserResponse {
    private String userId;

    private String firstName;
    private String lastName;
    private String email;
    private String dreamJob;
    private String authProvider;

    private SocialLinks socialLinks;

    @Data
    @AllArgsConstructor
    public static class SocialLinks {
        private String linkedin;
        private String github;
    }

    private Date createdAt;
    private Date updatedAt;
}
