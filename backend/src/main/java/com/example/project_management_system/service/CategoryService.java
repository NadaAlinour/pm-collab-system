package com.example.project_management_system.service;


import com.example.project_management_system.dto.CategoryResponseDTO;
import com.example.project_management_system.entity.Category;
import com.example.project_management_system.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) { this.categoryRepo = categoryRepo; }

    // fetch all statuses
    public List<CategoryResponseDTO> getCategories() {
        List<Category> categories = categoryRepo.findAll();

        // map category entity to category response dto
        return categories.stream().map(c -> new CategoryResponseDTO(c.getId(), c.getName())).toList();
    }
}
