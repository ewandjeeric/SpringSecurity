package com.example.demo.dao;

import com.example.demo.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
	public AppRole findByRole(String role);
	public AppRole findByRoleContaining(String role);

}
