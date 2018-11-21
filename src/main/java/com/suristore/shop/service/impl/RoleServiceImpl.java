package com.suristore.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suristore.shop.domain.Role;
import com.suristore.shop.repo.RoleRepository;
import com.suristore.shop.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public void save(String name) {
		Role role = new Role();
		role.setName(name);
		
		roleRepository.save(role);
		
	}

}
