package com.careercompass.message.model;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import lombok.Data;

import java.util.Date;

@Data
public class Message {
    @DocumentId
    private String messageId;

    private String role;

    private String content;

    @ServerTimestamp
    private Date createdAt;
}
