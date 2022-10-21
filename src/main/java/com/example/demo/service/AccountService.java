package com.example.demo.service;

import java.util.List;

import com.example.demo.models.AppRole;
import com.example.demo.models.AppUser;


public interface AccountService {
	public AppUser saveUser(AppUser u);

	public AppRole saveRole(AppRole r);

	public AppUser findByUsername(String username);

	public void AddRoleUser(String username, String role);

	public List<AppUser> listUser();

	public void AddRoleforUser(String username, String role);

	public AppUser findByEmail(String email);

}
