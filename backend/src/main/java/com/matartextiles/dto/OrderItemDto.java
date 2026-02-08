package com.matartextiles.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class OrderItemDto {
    private Long id;
    @NotNull private Long productId;
    private String productNameFr;
    private String productNameEn;
    private String productSlug;
    private String productImageUrl;
    @NotNull @Min(1) private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}
