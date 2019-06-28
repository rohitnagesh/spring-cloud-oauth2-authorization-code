package com.test.central.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.test.central.document.User;

public class AuthenticationDetails extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = -5647442789009312697L;

	private User detail;

	public AuthenticationDetails(User user) {
		super(user.getId(), user.getPassword(), getGrantedAuthorities(user));
		this.detail = user;
	}

	private static List<SimpleGrantedAuthority> getGrantedAuthorities(User user) {
		return user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
	}

	public User getDetail() {
		return detail;
	}

}