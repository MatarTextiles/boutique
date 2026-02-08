package com.matartextiles.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank @Email private String email;
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    private String phone;
    private String role;
    private String preferredLocale;
}
