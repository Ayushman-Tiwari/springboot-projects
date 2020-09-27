package com.ayushman.springweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayushman.springweb.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
