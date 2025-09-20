package com.careercompass.job_search.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class JobSearchClientConfig {

    @Bean
    public CollegeSearchClient restClientInterfaceJobSearch(RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder.baseUrl("https://resumeparserservice-118522306336.europe-west1.run.app").build();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return factory.createClient(CollegeSearchClient.class);
    }
}
