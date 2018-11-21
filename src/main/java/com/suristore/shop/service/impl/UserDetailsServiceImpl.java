package com.suristore.shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suristore.shop.domain.Role;
import com.suristore.shop.domain.User;
import com.suristore.shop.repo.UserRepository;
import com.suristore.shop.service.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		// Get roles of user
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		CustomUserDetails customUserDetails = new CustomUserDetails();
		customUserDetails.setUser(user);
		customUserDetails.setAuthorities(authorities);

		return customUserDetails;
	}

}