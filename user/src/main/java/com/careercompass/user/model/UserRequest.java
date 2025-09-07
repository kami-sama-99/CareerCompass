package com.careercompass.user.model;

import lombok.Data;

@Data
public class UserRequest {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String dreamJob;
    private String authProvider;

    private SocialLinks socialLinks;

    @Data
    public static class SocialLinks {
        private String linkedin;
        private String github;
    }
}
