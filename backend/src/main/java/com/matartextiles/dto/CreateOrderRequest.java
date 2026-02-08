package com.matartextiles.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateOrderRequest {
    @NotBlank private String shippingAddress;
    private String notes;
    @NotEmpty @Valid private List<OrderItemDto> items;
}
