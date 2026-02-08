package com.matartextiles.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CategoryDto {
    private Long id;
    @NotBlank private String nameFr;
    @NotBlank private String nameEn;
    @NotBlank private String slug;
    private String descriptionFr;
    private String descriptionEn;
    private String imageUrl;
    private Integer displayOrder;
}
