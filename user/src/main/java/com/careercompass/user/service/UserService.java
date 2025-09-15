package com.careercompass.user.service;

import lombok.RequiredArgsConstructor;
import com.careercompass.user.model.User;
import com.careercompass.user.model.UserRequest;
import com.careercompass.user.model.UserResponse;
import org.springframework.stereotype.Service;
import com.careercompass.user.repository.UserRepository;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse userOnboarding(UserRequest userRequest) throws ExecutionException, InterruptedException {
        User user = mapUserRequestToUser(userRequest);
        userRepository.save(user);
        return mapUserToUserResponse(user);
    }

    private UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setAuthProvider(user.getAuthProvider());
        userResponse.setDreamJob(user.getDreamJob());
        String linkedin = user.getSocialLinks().getLinkedin();
        String github = user.getSocialLinks().getGithub();
        userResponse.setSocialLinks(new UserResponse.SocialLinks(linkedin, github));
        return userResponse;
    }

    private User mapUserRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setUserId(userRequest.getUserId());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setDreamJob(userRequest.getDreamJob());
        user.setAuthProvider(userRequest.getAuthProvider());

        if (userRequest.getSocialLinks() != null) {
            User.SocialLinks links = new User.SocialLinks();
            links.setLinkedin(userRequest.getSocialLinks().getLinkedin());
            links.setGithub(userRequest.getSocialLinks().getGithub());
            user.setSocialLinks(links);
        }

        return user;
    }


    private boolean validateUserRequest(UserRequest userRequest) {
        if (userRequest == null) {
            return false;
        }

        if (userRequest.getFirstName() == null || userRequest.getFirstName().trim().isEmpty()) {
            return false;
        }

        if (userRequest.getLastName() == null || userRequest.getLastName().trim().isEmpty()) {
            return false;
        }

        if (userRequest.getEmail() == null || !userRequest.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return false;
        }

        if (userRequest.getDreamJob() == null || userRequest.getDreamJob().trim().isEmpty()) {
            return false;
        }

        if (userRequest.getAuthProvider() == null || userRequest.getAuthProvider().trim().isEmpty()) {
            return false;
        }

        if (userRequest.getSocialLinks() != null) {
            String linkedin = userRequest.getSocialLinks().getLinkedin();
            String github = userRequest.getSocialLinks().getGithub();

            if (linkedin != null && !linkedin.startsWith("http")) {
                return false;
            }

            if (github != null && !github.startsWith("http")) {
                return false;
            }
        }

        return true; // All checks passed
    }

    public UserResponse getProfile(String id) throws ExecutionException, InterruptedException {
        User user = userRepository.findById(id);
        return mapUserToUserResponse(user);
    }

    public UserResponse updateUser(UserRequest userRequest, String id) throws ExecutionException, InterruptedException {
        if (validateUserRequest(userRequest)) {
            User user = userRepository.update(id, mapUserRequestToUser(userRequest));
            return mapUserToUserResponse(user);
        }
        return null;
    }
}
