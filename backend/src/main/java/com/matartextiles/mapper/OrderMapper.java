package com.matartextiles.mapper;

import com.matartextiles.dto.OrderDto;
import com.matartextiles.dto.OrderItemDto;
import com.matartextiles.entity.Order;
import com.matartextiles.entity.OrderItem;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public OrderDto toDto(Order entity) {
        return OrderDto.builder()
                .id(entity.getId())
                .status(entity.getStatus().name())
                .totalAmount(entity.getTotalAmount())
                .shippingAddress(entity.getShippingAddress())
                .notes(entity.getNotes())
                .createdAt(entity.getCreatedAt())
                .items(entity.getItems().stream().map(this::toItemDto).collect(Collectors.toList()))
                .build();
    }

    public OrderItemDto toItemDto(OrderItem item) {
        return OrderItemDto.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .productNameFr(item.getProduct().getNameFr())
                .productNameEn(item.getProduct().getNameEn())
                .productSlug(item.getProduct().getSlug())
                .productImageUrl(item.getProduct().getImageUrl())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .subtotal(item.getSubtotal())
                .build();
    }
}
