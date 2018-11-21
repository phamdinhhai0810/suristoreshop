package com.suristore.shop.service;

import com.suristore.shop.domain.Role;

public interface RoleService {
	Role findByName(String name);
	
	void save(String name);
}
