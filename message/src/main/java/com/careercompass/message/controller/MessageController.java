package com.careercompass.message.controller;

import com.careercompass.message.model.MessageRequest;
import com.careercompass.message.model.MessageResponse;
import com.careercompass.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users/{userId}/mentor_sessions")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<MessageResponse> reply(
            @PathVariable String userId,
            @RequestBody MessageRequest messageRequest
            ) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(messageService.reply(userId, messageRequest));
    }
}
