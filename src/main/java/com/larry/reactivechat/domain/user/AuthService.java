package com.larry.reactivechat.domain.user;

import com.larry.reactivechat.controller.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    private final UserService userService;

    public User convertFromPrincipal(Principal principal) {
        return userService.findByEmail(principal.getEmail());
    }

}
