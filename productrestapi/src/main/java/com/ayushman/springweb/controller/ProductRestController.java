package com.ayushman.springweb.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ayushman.springweb.entity.Product;
import com.ayushman.springweb.repos.ProductRepository;

@RestController
public class ProductRestController {

	@Autowired
	ProductRepository repo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
	
	@GetMapping(value = "/products")
	public Optional<List<Product>> getProducts(){
		return Optional.of(repo.findAll());
	}
	
	@GetMapping(value = "/products/{id}")
	@Transactional(readOnly = true)
	@Cacheable("product-cache")
	public Optional<Product> findProduct(@PathVariable("id") int id){
		LOGGER.info("Finding product by id: "+id);
		return repo.findById(id);
	}
	
	@PostMapping(value = "/products")
	public Product createProduct(@RequestBody Product product) {
		return repo.save(product);
	}
	
	@PutMapping(value = "/products")
	public Product updateProduct(@RequestBody Product product) {
		return repo.save(product);
	}
	
	@DeleteMapping(value = "/products/{id}")
	@CacheEvict("product-cache")
	public void deleteProduct(@PathVariable("id") int id) {
		repo.deleteById(id);
	}
	
	
}
