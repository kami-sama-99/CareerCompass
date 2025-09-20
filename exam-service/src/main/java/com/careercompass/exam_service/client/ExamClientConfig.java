package com.careercompass.exam_service.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ExamClientConfig {

    @Bean
    public ExamClient restClientInterfaceResume(RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder.baseUrl("http://127.0.0.1:8000").build();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return factory.createClient(ExamClient.class);
    }
}
