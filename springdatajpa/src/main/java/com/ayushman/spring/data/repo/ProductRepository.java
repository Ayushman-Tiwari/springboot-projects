package com.ayushman.spring.data.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ayushman.spring.data.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	List<Product> findByName(String name);
	
	Optional<List<Product>> findByPrice(Double price);
	
	Optional<List<Product>> findByNameAndPrice(String name, Double price);
	
}
