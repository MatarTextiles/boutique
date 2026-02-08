package com.matartextiles.controller;

import com.matartextiles.dto.CreateOrderRequest;
import com.matartextiles.dto.OrderDto;
import com.matartextiles.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> create(
            Authentication authentication,
            @Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(authentication.getName(), request));
    }

    @GetMapping
    public ResponseEntity<Page<OrderDto>> getMyOrders(
            Authentication authentication,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrdersByUser(authentication.getName(), pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(
            Authentication authentication,
            @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id, authentication.getName()));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(orderService.updateStatus(id, body.get("status")));
    }
}
