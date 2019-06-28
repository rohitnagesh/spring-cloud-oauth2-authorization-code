package com.test.user.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.user.constants.UserTypes;

@RestController
public class UserTypeController {

	@GetMapping(value = "userTypes")
	public Map<String, String> getUserTypes() {
		Map<String, String> userTypes = Arrays.stream(UserTypes.values())
				.collect(Collectors.toMap(UserTypes::name, UserTypes::toString));
		return userTypes;
	}

}