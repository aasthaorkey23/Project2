package com.RBU.Project2.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RBU.Project2.Repositories.CategoryRepository;
import com.RBU.Project2.entities.Category;
import com.RBU.Project2.security.CustomUserDetails;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryRepository categoryRepository;
	public CategoryController (CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Category> createCategory(@RequestBody Category category, @AuthenticationPrincipal CustomUserDetails userDetails ){
		category.setUserId(userDetails.getUserId());
		Category savedCategory = categoryRepository.save(category);
		return ResponseEntity.status(201).body(savedCategory);
	}
	
	@GetMapping("/get")
	public List<Category> getCategory(){
		return categoryRepository.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Optional<Category> getCategory(@PathVariable Long id) {
		return categoryRepository.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteByName(@PathVariable String id) {
		Category cat = categoryRepository.findByName(id);
		categoryRepository.delete(cat);
		
	}
}
