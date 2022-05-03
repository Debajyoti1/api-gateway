package com.debu.microservice.apigateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				/*.route(p->p.path("/get")
					.filters(f->f.addRequestHeader("test", "testing"))
					.uri("http://httpbin.org:80"))*/
				.route(p->p.path("/app1/**")
						.filters(f->f.rewritePath("/app1","/"))
						.uri("lb://app1/"))
				.route(p->p.path("/app2/**")
						.filters(f->f.rewritePath("/app2", "/"))
						.uri("lb://app2"))
				.route(p->p.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))
				.route(p->p.path("/gg")
						.filters(f->f.rewritePath("/gg", "/"))
						.uri("https://ggfiles.cf"))
				
				
				
				.build();
	}
}
