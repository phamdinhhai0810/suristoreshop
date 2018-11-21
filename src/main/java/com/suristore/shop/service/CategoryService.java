package com.suristore.shop.service;

import com.suristore.shop.domain.Category;

public interface CategoryService {

	Iterable<Category> findAll();

	Category findOne(int id);

	long countAll();

	Category save(Category category);

	void delete(int id);

}
