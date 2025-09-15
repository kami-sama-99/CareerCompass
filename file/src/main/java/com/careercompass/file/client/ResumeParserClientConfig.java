package com.careercompass.file.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ResumeParserClientConfig {

    @Bean
    public ResumeParserClient restClientInterfaceResume(RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder.baseUrl("https://5c84fcfc6905.ngrok-free.app").build();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return factory.createClient(ResumeParserClient.class);
    }
}
