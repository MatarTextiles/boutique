package com.matartextiles.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class OrderDto {
    private Long id;
    private String status;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private String notes;
    private List<OrderItemDto> items;
    private LocalDateTime createdAt;
}
