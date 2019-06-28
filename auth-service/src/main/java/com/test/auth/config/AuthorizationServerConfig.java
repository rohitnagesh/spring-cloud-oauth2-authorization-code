package com.test.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()") //
				.checkTokenAccess("isAuthenticated()") //
				.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory() //
				.withClient("trusted-app") //
				.secret(passwordEncoder.encode("secret")) //
				.authorizedGrantTypes("password", "refresh_token") //
				.scopes("openid") //
				.accessTokenValiditySeconds(60 * 60 * 24 * 1) // Access token validity of 1 day
				.refreshTokenValiditySeconds(60 * 60 * 24 * 30) // Access token validity of 30 days
		;
	}

}