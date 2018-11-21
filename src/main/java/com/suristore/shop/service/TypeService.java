package com.suristore.shop.service;

import com.suristore.shop.domain.Type;

public interface TypeService {
	Iterable<Type> findAll();

	Type findOne(int id);

	long countAll();

	Type save(Type category);

	void delete(int id);
}
