package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        userRepository.create(user);
        return user;
    }

    @Transactional
    public User updateUser(User user) {
        userRepository.update(user);
        return user;
    }

    @Transactional
    public void deleteUser(UUID id) {
        userRepository.delete(id);
    }

    public User getUser(UUID id) {
        return userRepository.get(id);
    }
}
