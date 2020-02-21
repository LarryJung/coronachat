package com.larry.reactivechat.domain.user;

import com.larry.reactivechat.controller.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User checkForLogin(LoginDto loginDto) {
        User user = findByEmail(loginDto.getEmail());
        if (!user.isPassword(loginDto.getPassword())) {
            throw new RuntimeException("password not matched");
        }
        return user;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not exist"));
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("cannot find user"));
    }
}
