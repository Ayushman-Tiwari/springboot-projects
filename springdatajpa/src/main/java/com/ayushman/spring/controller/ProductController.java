package com.ayushman.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayushman.spring.data.entity.Product;
import com.ayushman.spring.data.repo.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductRepository repository;

	@GetMapping
	public Optional<List<Product>> getProducts() {

		Iterable<Product> product = repository.findAll();

		return Optional.of((List<Product>) product);

	}

	@PostMapping
	public Product create(@RequestBody Product product) {
		return repository.save(product);
	}
	
	@PutMapping
	public Product update(@RequestBody Product product) {
		return repository.save(product);
	}
	
	@GetMapping("/{id}")
	public Optional<Product> getProduct(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {
		repository.deleteById(id);
	}

}
