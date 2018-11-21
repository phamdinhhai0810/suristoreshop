package com.suristore.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suristore.shop.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
}
