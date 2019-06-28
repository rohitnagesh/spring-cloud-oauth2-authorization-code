package com.test.central.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.token.ClientKeyGenerator;
import org.springframework.security.oauth2.client.token.DefaultClientKeyGenerator;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.test.central.config.support.CustomOAuth2Filter;

@Configuration
@Order(-20)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http//
			.csrf().disable() //
			.exceptionHandling() //
				.authenticationEntryPoint(authenticationEntryPoint()) //
			.and().requestMatchers().antMatchers("/login", "/oauth/authorize") //
			.and().authorizeRequests()
				.antMatchers("/user/me").permitAll() //
				.antMatchers("/login", "/signup/**", "/forgotPassword/**").permitAll() //
				.antMatchers("/manage/**").permitAll() //
				.antMatchers("/organisation/**").permitAll() //
				.anyRequest().authenticated() //
			.and().antMatcher("/oauth/authorize").addFilterAt(customOAuth2Filter(), BasicAuthenticationFilter.class) //
		;
		// @formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	protected AuthenticationEntryPoint authenticationEntryPoint() {
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
	
	@Bean
	public AuthenticationKeyGenerator authenticationKeyGenerator() {
		return new DefaultAuthenticationKeyGenerator();
	}

	@Bean
	public ClientKeyGenerator clientKeyGenerator() {
		return new DefaultClientKeyGenerator();
	}
	
	protected CustomOAuth2Filter customOAuth2Filter() throws Exception {
		CustomOAuth2Filter customOAuth2Filter = new CustomOAuth2Filter(authenticationManagerBean());
		customOAuth2Filter.setContext(getApplicationContext());
		return customOAuth2Filter;
	}

}