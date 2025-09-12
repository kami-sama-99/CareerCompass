package com.careercompass.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public GlobalFilter logFilter() {
		return (exchange, chain) -> {
			System.out.println("Gateway received request: " + exchange.getRequest().getPath());
			return chain.filter(exchange).then(Mono.fromRunnable(() ->
					System.out.println("Gateway response status: " + exchange.getResponse().getStatusCode())
			));
		};
	}

}
