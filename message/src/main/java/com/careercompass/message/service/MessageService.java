package com.careercompass.message.service;

import com.careercompass.message.model.Message;
import com.careercompass.message.model.MessageRequest;
import com.careercompass.message.model.MessageResponse;
import com.careercompass.message.model.Session;
import com.careercompass.message.repository.MessageRepository;
import com.careercompass.message.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final SessionRepository sessionRepository;
    
    public MessageResponse reply(String userId, MessageRequest messageRequest) throws ExecutionException, InterruptedException {
        if (messageRequest.getSessionId().trim().isEmpty()) {
            Session session = new Session();
            session.setStatus("active");
            messageRequest.setSessionId(sessionRepository.save(userId, session));
        }
        return mapMessageToMessageResponse(messageRepository.save(userId, messageRequest.getSessionId(), mapMessageRequestToMessage(messageRequest)), messageRequest.getSessionId());
    }

    private Message mapMessageRequestToMessage(MessageRequest messageRequest) {
        Message message = new Message();
        message.setContent(messageRequest.getMessage());
        message.setRole("user");
        return message;
    }

    private MessageResponse mapMessageToMessageResponse(Message message, String sessionId) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setReply(message.getContent());
        messageResponse.setSessionId(sessionId);
        return messageResponse;
    }
}
