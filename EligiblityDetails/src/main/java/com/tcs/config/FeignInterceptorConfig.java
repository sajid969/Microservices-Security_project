package com.tcs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignInterceptorConfig {
	
	@Bean
    public RequestInterceptor requestInterceptor() {
        return new CustomFeignInterceptor();
    }
	public static class CustomFeignInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate template) {
        	 // Retrieve the token from the current request's header
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String jwttoken = attributes.getRequest().getHeader("jwttoken");
            System.out.println("jwttoken : "+jwttoken);
            if (jwttoken != null && !jwttoken.isEmpty()) {
                template.header("Authorization", "Bearer " + jwttoken);
            }
        }

		
    }

}
