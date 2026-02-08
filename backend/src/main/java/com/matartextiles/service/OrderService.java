package com.matartextiles.service;

import com.matartextiles.dto.CreateOrderRequest;
import com.matartextiles.dto.OrderDto;
import com.matartextiles.dto.OrderItemDto;
import com.matartextiles.entity.*;
import com.matartextiles.exception.BadRequestException;
import com.matartextiles.exception.ResourceNotFoundException;
import com.matartextiles.mapper.OrderMapper;
import com.matartextiles.repository.OrderRepository;
import com.matartextiles.repository.ProductRepository;
import com.matartextiles.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderDto createOrder(String userEmail, CreateOrderRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = Order.builder()
                .user(user)
                .shippingAddress(request.getShippingAddress())
                .notes(request.getNotes())
                .totalAmount(BigDecimal.ZERO)
                .build();

        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemDto itemDto : request.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + itemDto.getProductId()));

            if (product.getStockQuantity() < itemDto.getQuantity()) {
                throw new BadRequestException("Insufficient stock for: " + product.getNameFr());
            }

            BigDecimal unitPrice = product.getPrice();
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(itemDto.getQuantity()));

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemDto.getQuantity())
                    .unitPrice(unitPrice)
                    .subtotal(subtotal)
                    .build();

            order.getItems().add(orderItem);
            total = total.add(subtotal);

            product.setStockQuantity(product.getStockQuantity() - itemDto.getQuantity());
            productRepository.save(product);
        }

        order.setTotalAmount(total);
        return orderMapper.toDto(orderRepository.save(order));
    }

    public Page<OrderDto> getOrdersByUser(String userEmail, Pageable pageable) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return orderRepository.findByUserIdOrderByCreatedAtDesc(user.getId(), pageable)
                .map(orderMapper::toDto);
    }

    public OrderDto getOrderById(Long id, String userEmail) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        if (!order.getUser().getEmail().equals(userEmail)) {
            throw new BadRequestException("Access denied");
        }
        return orderMapper.toDto(order);
    }

    public OrderDto updateStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        order.setStatus(Order.Status.valueOf(status));
        return orderMapper.toDto(orderRepository.save(order));
    }
}
