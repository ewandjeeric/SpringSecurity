package com.example.demo.dao;

import com.example.demo.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String username);

	public AppUser findByUsernameContaining(String username);

	public AppUser findByEmail(String email);
}
