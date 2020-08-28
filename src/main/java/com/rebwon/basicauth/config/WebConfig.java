package com.rebwon.basicauth.config;

import com.rebwon.basicauth.auth.BasicAuthInterceptor;
import com.rebwon.basicauth.auth.AuthAccountHandlerMethodArgumentResolver;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Bean
  public BasicAuthInterceptor basicAuthInterceptor() {
    return new BasicAuthInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(basicAuthInterceptor());
  }

  @Bean
  public AuthAccountHandlerMethodArgumentResolver loginedHandlerMethodArgumentResolver() {
    return new AuthAccountHandlerMethodArgumentResolver();
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(loginedHandlerMethodArgumentResolver());
  }
}
