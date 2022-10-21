package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.dao.AppRoleRepository;
import com.example.demo.dao.AppUserRepository;
import com.example.demo.models.AppRole;
import com.example.demo.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	AppRoleRepository approlerepo;
	@Autowired
	AppUserRepository appuserrepo;

	@Override
	public AppUser saveUser(AppUser u) {
		u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
		return appuserrepo.save(u);
	}

	@Override
	public AppRole saveRole(AppRole r) {
		return approlerepo.save(r);
	}

	@Override
	public AppUser findByUsername(String username) {
		return appuserrepo.findByUsername(username);
	}

	@Override
	public void AddRoleUser(String username, String r) {
		AppUser user = appuserrepo.findByUsername(username);
		AppRole role = approlerepo.findByRole(r);
		user.getRoles().add(role);

	}

	@Override
	public List<AppUser> listUser() {
		return appuserrepo.findAll();
	}

	@Override
	public void AddRoleforUser(String username, String r) {

		AppUser user = appuserrepo.findByUsernameContaining(username);
		AppRole role = approlerepo.findByRoleContaining(r);
		user.getRoles().add(role);

		appuserrepo.save(user);

	}

	@Override
	public AppUser findByEmail(String email) {
		return appuserrepo.findByEmail(email);
	}

}
