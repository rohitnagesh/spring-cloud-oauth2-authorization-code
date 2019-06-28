package com.test.central.config.support;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class CustomOAuth2Filter extends BasicAuthenticationFilter {

	@Autowired
	public CustomOAuth2Filter(@Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	private ApplicationContext context;

	@SuppressWarnings("unused")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		if ("/authorize".equals(request.getRequestURI()) || "/oauth/authorize".equals(request.getRequestURI())) {
			OAuth2AuthenticationProcessingFilter oauth2AuthenticationProcessingFilter = (OAuth2AuthenticationProcessingFilter) getFilter(
					"OAuth2AuthenticationProcessingFilter");
			oauth2AuthenticationProcessingFilter.doFilter(request, response, chain);
		} else {
			super.doFilter(request, response, chain);
		}
	}

	public Filter getFilter(String filterName) {
		FilterChainProxy filterChainProxy = (FilterChainProxy) context.getBean("springSecurityFilterChain");
		List<SecurityFilterChain> list = filterChainProxy.getFilterChains();
		return list.stream().flatMap(chain -> chain.getFilters().stream())
				.filter(filter -> filter.getClass().getName().contains(filterName)).findAny().get();
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

}