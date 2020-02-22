package com.larry.reactivechat.util;

import com.larry.reactivechat.controller.Principal;
import com.larry.reactivechat.domain.user.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        return exchange.getSession()
                .map(session -> {
                    Object maybePrincipal = session.getAttribute("login");
                    if (maybePrincipal == null) {
                        throw new RuntimeException("Login required!");
                    }
                    return (Principal) maybePrincipal;
                });
    }

}
