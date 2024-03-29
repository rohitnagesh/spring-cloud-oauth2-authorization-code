package com.test.gateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
				.and().authorizeRequests() //
				.antMatchers("/login", "/uaa/**").permitAll() //
				.antMatchers("/manage/**").permitAll() //
				.anyRequest().authenticated();
	}

}