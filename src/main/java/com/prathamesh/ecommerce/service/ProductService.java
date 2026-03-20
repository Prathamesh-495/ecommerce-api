package com.prathamesh.ecommerce.service;

import com.prathamesh.ecommerce.dto.ProductRequest;
import com.prathamesh.ecommerce.dto.ProductResponse;
import com.prathamesh.ecommerce.entity.Category;
import com.prathamesh.ecommerce.entity.Product;
import com.prathamesh.ecommerce.repository.CategoryRepository;
import com.prathamesh.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductResponse createProduct(ProductRequest request){
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new RuntimeException("category not found "+request.getCategoryId()));
        Product product = new Product();
        product.setCategory(category);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setImageUrl(request.getImageUrl());

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }

    private ProductResponse mapToResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setStockQuantity(product.getStockQuantity());
        response.setPrice(product.getPrice());
        response.setImageUrl(product.getImageUrl());
        response.setCategoryName(product.getCategory().getName());
        return response;
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll()
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("product not found "+id));
        productRepository.delete(product);
    }

    public ProductResponse getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("product not found "+id));
        return mapToResponse(product);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request){
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("product not found "+id));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new RuntimeException("category not found "+request.getCategoryId()));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category); //

        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }
}
