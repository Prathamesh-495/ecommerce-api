package com.prathamesh.ecommerce.repository;

import com.prathamesh.ecommerce.entity.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findByUserId(Long userId);


    Optional<CartItem> findByUserIdAndProductId(Long userId,Long productId);

    @Transactional
    void deleteByUserId(Long userId);
}
