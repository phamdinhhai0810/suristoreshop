package com.suristore.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suristore.shop.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
