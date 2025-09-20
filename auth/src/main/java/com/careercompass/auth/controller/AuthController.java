package com.careercompass.auth.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.web.bind.annotation.*;
import com.careercompass.auth.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestHeader String firebaseIdToken) throws Exception {
        // Verify Firebase token
        FirebaseToken decoded = FirebaseAuth.getInstance().verifyIdToken(firebaseIdToken);

        String uid = decoded.getUid();
        String role = "USER";

        // Generate JWT for session
        return jwtUtil.generateToken(uid, role);
    }
}