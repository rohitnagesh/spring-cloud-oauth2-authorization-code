package com.test.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.auth.document.User;
import com.test.auth.model.AuthenticationDetails;
import com.test.auth.repo.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Attempting login with " + username);
		User user = userRepo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new AuthenticationDetails(user);
	}

}