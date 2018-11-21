package com.suristore.shop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.suristore.shop.domain.Product;

public interface ProductService {

	Iterable<Product> findAll();

	List<Product> findLatest(int page, int size);

	List<Product> search(String keyword);

	List<Product> findByCategoryId(int categoryId);

	Product findOne(int id);

	Product findOneWithCategory(int id);

	long countAll();

	Product save(Product product);

	void delete(int id);

	Product upload(Product product, MultipartFile imageFile);

}
