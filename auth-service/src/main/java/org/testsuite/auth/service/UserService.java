package org.testsuite.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.testsuite.auth.entities.jpa.User;
import org.testsuite.auth.repository.jpa.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(Exception::new);
    }
}
