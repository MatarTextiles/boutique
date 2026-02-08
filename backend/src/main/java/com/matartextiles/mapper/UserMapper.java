package com.matartextiles.mapper;

import com.matartextiles.dto.UserDto;
import com.matartextiles.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phone(entity.getPhone())
                .role(entity.getRole().name())
                .preferredLocale(entity.getPreferredLocale())
                .build();
    }

    public void updateEntity(User entity, UserDto dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        if (dto.getPreferredLocale() != null) entity.setPreferredLocale(dto.getPreferredLocale());
    }
}
