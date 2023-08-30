package com.tcs.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationFilter extends AbstractGatewayFilterFactory<MyAuthenticationFilter.Config> {

	@Autowired
	public JwtUtils jwtUtil;

	@Autowired
	public RouteValidator validator;

	@Override
	public GatewayFilter apply(Config config) {
		 return ((exchange, chain) -> {
	            if (validator.isSecured.test(exchange.getRequest())) {
	                //header contains token or not
	                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
	                    throw new RuntimeException("missing authorization header");
	                }

	                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
	                ServerHttpRequest userRequest;
	                if (authHeader != null && authHeader.startsWith("Bearer ")) {
	                    authHeader = authHeader.substring(7);
						
	                }
	                try {
//	                    //REST call to AUTH service
//	                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
	                    jwtUtil.validateToken(authHeader);

	                } catch (Exception e) {
	                   
	                    throw new RuntimeException("un authorized access to application");
	                }
	                String username = jwtUtil.extractUsername(authHeader);
					
					userRequest = exchange.getRequest().mutate().header("loggedInUser", username).header("jwttoken", authHeader).build();
					return chain.filter(exchange.mutate().request(userRequest).build());
	            }
	            return chain.filter(exchange);
		});
	}

	public MyAuthenticationFilter() {
		super(Config.class);
	}

	public static class Config {

	}

}
