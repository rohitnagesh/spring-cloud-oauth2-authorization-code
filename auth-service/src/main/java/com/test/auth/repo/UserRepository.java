package com.test.auth.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.auth.document.User;

public interface UserRepository extends MongoRepository<User, String> {

	/**
	 * Find a user by email address
	 */
	public User findByEmail(String email);

}