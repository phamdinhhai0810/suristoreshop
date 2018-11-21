package com.suristore.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suristore.shop.domain.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}