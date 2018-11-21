package com.suristore.shop;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.suristore.shop.domain.Role;
import com.suristore.shop.domain.User;
import com.suristore.shop.repo.RoleRepository;
import com.suristore.shop.repo.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
public class SpringShopAdminApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringShopAdminApplication.class, args);

	}

	private void initRole() {

		Role role = new Role();
		role.setName("ROLE_ADMIN");
		roleRepository.save(role);
	}

	private void initUser() {

		Role role = roleRepository.findByName("ROLE_ADMIN");
		Set<Role> roles = new HashSet<>();
		roles.add(role);

		User user = new User();
		user.setEmail("admin@gmail.com");
		user.setName("admin");
		user.setPassword("$2a$10$Fd/rhPgkG6XbufIneTSMxeRFLCwl3OShb5J7woRqTIuepHTwK7XXO");
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Override
	public void run(String... args) throws Exception {
		 initRole();
		 initUser();
	}
}
