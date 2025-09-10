package com.careercompass.message.model;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import lombok.Data;

import java.util.Date;

@Data
public class Session {
    @DocumentId
    private String sessionId;

    private String status;

    @ServerTimestamp
    private Date createdAt;

    @ServerTimestamp
    private Date endedAt;
}
