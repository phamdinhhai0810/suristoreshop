package com.suristore.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suristore.shop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}