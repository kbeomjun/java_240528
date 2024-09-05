package kr.kh.spring3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.kh.spring3.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Autowired
	LoginInterceptor loginInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor )
	                .addPathPatterns("/guest/login");
    }
}