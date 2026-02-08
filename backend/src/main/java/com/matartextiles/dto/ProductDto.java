package com.matartextiles.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotBlank private String nameFr;
    @NotBlank private String nameEn;
    @NotBlank private String slug;
    private String descriptionFr;
    private String descriptionEn;
    @NotNull @DecimalMin("0.01") private BigDecimal price;
    private String imageUrl;
    private String sku;
    private Integer stockQuantity;
    private Boolean active;
    @NotNull private Long categoryId;
    private String categorySlug;
    private String categoryNameFr;
    private String categoryNameEn;
}
