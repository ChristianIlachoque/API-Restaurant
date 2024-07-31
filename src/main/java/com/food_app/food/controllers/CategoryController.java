package com.food_app.food.controllers;

import com.food_app.food.dto.CategoryRequestDTO;
import com.food_app.food.dto.CategoryResponseDTO;
import com.food_app.food.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<?> getALl(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        CategoryResponseDTO categoryResponseDTO = categoryService.getById(id);
        if(categoryResponseDTO != null) return ResponseEntity.ok(categoryResponseDTO);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryRequestDTO categoryRequest){
        return ResponseEntity.ok(categoryService.create(categoryRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody CategoryRequestDTO categoryRequestDTO){
        CategoryResponseDTO categoryResponseDTO = categoryService.getById(id);
        if(categoryResponseDTO == null) return ResponseEntity.notFound().build();
        categoryResponseDTO = categoryService.update(id, categoryRequestDTO);
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        boolean wasDeleted = categoryService.delete(id);
        if(wasDeleted) return ResponseEntity.noContent().build();
        return ResponseEntity.ok().build();
    }
}
