package com.careercompass.message.model;

import lombok.Data;

@Data
public class MessageRequest {
    private String sessionId;
    private String message;
}
