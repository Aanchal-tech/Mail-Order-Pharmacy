package com.cts.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class RouteConfigurations {
	// Hit this url to start the project
	//http://localhost:8048/ui-service/
	@Bean
	public  RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("auth-service", r -> r.path("/auth-service/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8090"))
				.route("drug-service", r -> r.path("/drug-service/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8081"))
				.route("refill-service", r -> r.path("/refill-service/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8454"))
				.route("subscription-service", r -> r.path("/subscription-service/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8082"))				
				.route("ui-service", r -> r.path("/ui-service/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8080"))				
				.build();
	}

}
