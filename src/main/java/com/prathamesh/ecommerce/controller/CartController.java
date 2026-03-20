package com.prathamesh.ecommerce.controller;

import com.prathamesh.ecommerce.dto.CartItemResponse;
import com.prathamesh.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/")
    public ResponseEntity<List<CartItemResponse>> getCart(){
        return ResponseEntity.ok(cartService.getCart());
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(){
        cartService.clearCart();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    public ResponseEntity<CartItemResponse> addToCart(@RequestParam Long productId,@RequestParam int quantity){
        return ResponseEntity.ok(cartService.addToCart(productId,quantity));
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartItemId){
        cartService.removeFromCart(cartItemId);
        return ResponseEntity.noContent().build();
    }
}
