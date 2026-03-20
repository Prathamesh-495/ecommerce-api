package com.prathamesh.ecommerce.service;

import com.prathamesh.ecommerce.dto.CategoryRequest;
import com.prathamesh.ecommerce.dto.CategoryResponse;
import com.prathamesh.ecommerce.entity.Category;
import com.prathamesh.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category savedCategory = categoryRepository.save(category);

        return mapToResponse(savedCategory);
    }

    public List<CategoryResponse> getAllCategories(){
        return categoryRepository.findAll()
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private CategoryResponse mapToResponse(Category category){
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }

    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("category not found "+id));
        categoryRepository.delete(category);
    }

    public CategoryResponse getCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("category not found "+id));
        return mapToResponse(category);
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request){
        Category category = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("category not found "+id));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return mapToResponse(updatedCategory);
    }
}
