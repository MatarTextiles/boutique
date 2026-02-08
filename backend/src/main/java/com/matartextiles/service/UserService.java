package com.matartextiles.service;

import com.matartextiles.dto.UserDto;
import com.matartextiles.entity.User;
import com.matartextiles.exception.ResourceNotFoundException;
import com.matartextiles.mapper.UserMapper;
import com.matartextiles.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    public UserDto updateProfile(String email, UserDto dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userMapper.updateEntity(user, dto);
        return userMapper.toDto(userRepository.save(user));
    }
}
