package com.prathamesh.ecommerce.service;

import com.prathamesh.ecommerce.dto.CartItemResponse;
import com.prathamesh.ecommerce.entity.CartItem;
import com.prathamesh.ecommerce.entity.Product;
import com.prathamesh.ecommerce.entity.User;
import com.prathamesh.ecommerce.repository.CartItemRepository;
import com.prathamesh.ecommerce.repository.ProductRepository;
import com.prathamesh.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public CartItemResponse addToCart(Long productId,int quantity){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("product not found "+productId));

        Optional<CartItem> existing = cartItemRepository.findByUserIdAndProductId(user.getId(), product.getId());

        CartItem cartItem;

        if(product.getStockQuantity() <= 0) {
            throw new RuntimeException("Product out of stock: " + productId);
        }

        if(existing.isPresent()){
            cartItem = existing.get();
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
        }else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }

        CartItem saved = cartItemRepository.save(cartItem);
        return mapToResponse(saved);

    }

    private CartItemResponse mapToResponse(CartItem cartItem){
        BigDecimal subtotal = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));

        return new CartItemResponse(
                cartItem.getId(),
                cartItem.getProduct().getId(),
                cartItem.getProduct().getName(),
                cartItem.getProduct().getPrice(),
                cartItem.getQuantity(),
                subtotal);
    }

    public List<CartItemResponse> getCart(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        return cartItemRepository.findByUserId(user.getId()).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Transactional
    public void clearCart(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        cartItemRepository.deleteByUserId(user.getId());
    }

    public void removeFromCart(Long cartItemId){

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()->new RuntimeException("cart item not found " + cartItemId));

        cartItemRepository.deleteById(cartItemId);
    }
}
