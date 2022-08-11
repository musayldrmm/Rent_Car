package com.rent.rentcar.service;

import com.rent.rentcar.entity.Category;
import com.rent.rentcar.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryrepository;

    public Category addCategory(Category category) {
        return categoryrepository.save(category);
    }

    public List<Category> getAllCategory() {
        return categoryrepository.findAll();
    }

    public Category getByIdCategory(Long categoryId) {
        return categoryrepository.findById(categoryId).get();
    }

    public void removeCategory(Long categoryId) {
        categoryrepository.deleteById(categoryId);
    }
}
