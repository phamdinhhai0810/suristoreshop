package com.suristore.shop.service;

import java.util.List;

import com.suristore.shop.domain.User;

public interface UserService {

	List<User> findAll();

	User findOne(int id);

	long countAll();

	void delete(int id);

	boolean register(User user);


}
