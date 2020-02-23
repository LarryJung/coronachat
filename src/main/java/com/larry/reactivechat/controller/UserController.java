package com.larry.reactivechat.controller;

import com.larry.reactivechat.domain.user.User;
import com.larry.reactivechat.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }



    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginDto loginDto, WebSession session) {
        if (session.getAttribute("login") != null) {
            throw new RuntimeException("Already login!");
        }
        User user = userService.checkForLogin(loginDto);
        session.getAttributes().put("login", new Principal(user.getId(), user.getEmail(), user.getName()));
    }

    @PostMapping("/logout")
    public void logout(WebSession session) {
        session.getAttributes().remove("login");
        session.getAttributes().forEach((k, v) -> System.out.println(k + " : " + v));
    }

}
