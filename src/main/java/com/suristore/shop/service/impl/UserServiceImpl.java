package com.suristore.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suristore.shop.domain.User;
import com.suristore.shop.repo.UserRepository;
import com.suristore.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public long countAll() {
		return userRepository.count();
	}

	@Override
	@Transactional
	public void delete(int id) {
		userRepository.delete(id);
	}

	@Override
	@Transactional
	public boolean register(User user) {
		// Check email exists
		if (userRepository.findByEmail(user.getEmail()) != null) {
			return false;
		}

		userRepository.save(user);

		return true;
	}

}
