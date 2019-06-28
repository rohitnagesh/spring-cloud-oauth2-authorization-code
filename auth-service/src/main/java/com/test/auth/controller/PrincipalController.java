package com.test.auth.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {

	private static final Logger logger = LoggerFactory.getLogger(PrincipalController.class);

	@GetMapping("/user")
	public Principal getUser(Principal user) {
		logger.debug("Entering PrincipalController.getUser()");
		return user;
	}

}