package com.suristore.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suristore.shop.domain.Category;
import com.suristore.shop.repo.CategoryRepository;
import com.suristore.shop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public long countAll() {
		return categoryRepository.count();
	}

	@Override
	@Transactional
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void delete(int id) {
		categoryRepository.delete(id);
	}

}
