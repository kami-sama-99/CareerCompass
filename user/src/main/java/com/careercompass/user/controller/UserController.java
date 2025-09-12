package com.careercompass.user.controller;

import lombok.RequiredArgsConstructor;
import com.careercompass.user.model.UserRequest;
import com.careercompass.user.model.UserResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.careercompass.user.service.UserService;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/onboarding")
    public ResponseEntity<UserResponse> userOnboarding(
            @RequestBody UserRequest userRequest
            ) throws ExecutionException, InterruptedException {
        UserResponse userResponse = userService.userOnboarding(userRequest);
        if (userResponse != null) {
            return new ResponseEntity<>(userResponse, HttpStatusCode.valueOf(201));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserResponse> getUserProfile(
            @PathVariable String id
    ) throws ExecutionException, InterruptedException {
        UserResponse userResponse = userService.getProfile(id);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<UserResponse> updateUserProfile(
            @RequestBody UserRequest userRequest,
            @RequestParam String id
    ) throws ExecutionException, InterruptedException {
        UserResponse userResponse = userService.updateUser(userRequest, id);
        if (userResponse != null) {
            return ResponseEntity.ok(userResponse);
        }
        return ResponseEntity.badRequest().build();
    }
}
