package com.rent.rentcar.controller;

import com.rent.rentcar.entity.Category;
import com.rent.rentcar.exceptions.GetByIdErrorMessage;
import com.rent.rentcar.exceptions.RemoveByIdErrorMessage;
import com.rent.rentcar.repository.CategoryRepository;
import com.rent.rentcar.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")

public class CategoryController {
    @Autowired
    private CategoryService categoryservice;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/save")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryservice.addCategory(category));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> findAllCategory() {
        return ResponseEntity.ok(categoryservice.getAllCategory());
    }

    @GetMapping("/find-category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) throws GetByIdErrorMessage {
        if (!categoryRepository.existsById(id)) {
            throw new GetByIdErrorMessage("Böyle bir kategori bulunmamakta");
        }
        return ResponseEntity.ok(categoryservice.getByIdCategory(id));
    }

    @DeleteMapping("/remove-category/{id}")
    public ResponseEntity<Category> removeCategory(@PathVariable Long id) throws RemoveByIdErrorMessage {
        if (!categoryRepository.existsById(id)) {
            throw new RemoveByIdErrorMessage("Silmek istediğiniz kategori kayıdı bulunmamakta");
        }
        categoryservice.removeCategory(id);
        return new ResponseEntity<Category>(HttpStatus.ACCEPTED);
    }

}
