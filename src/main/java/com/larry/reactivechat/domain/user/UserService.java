package com.larry.reactivechat.domain.user;

import com.larry.reactivechat.controller.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean isValidForLogin(LoginDto loginDto) {
        return userRepository.findByEmail(loginDto.getEmail())
                .map(user -> user.isPassword(loginDto.getPassword()))
                .orElse(false);
    }
}
