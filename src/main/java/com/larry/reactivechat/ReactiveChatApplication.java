package com.larry.reactivechat;

import com.larry.reactivechat.domain.user.AuthService;
import com.larry.reactivechat.util.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
@SpringBootApplication
public class ReactiveChatApplication implements WebFluxConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveChatApplication.class, args);
    }

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(loginUserArgumentResolver());
    }

    @Bean
    public HandlerMethodArgumentResolver loginUserArgumentResolver() {
        return new LoginUserHandlerMethodArgumentResolver();
    }

}
