package com.prathamesh.ecommerce.controller;

import com.prathamesh.ecommerce.dto.OrderResponse;
import com.prathamesh.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(){
        return ResponseEntity.status(201).body(orderService.placeOrder());
    }

    @GetMapping("/history")
    public ResponseEntity<List<OrderResponse>> getOrderHistory(){
        return ResponseEntity.ok(orderService.getOrderHistory());
    }
}
