package com.careercompass.file.client;

import com.careercompass.file.model.ParsedResume;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface ResumeParserClient {

    @PostExchange(value = "/upload-resume", contentType = "multipart/form-data")
    public ParsedResume getParsedResume(
            @RequestPart("file") MultipartFile resume
    );
}
