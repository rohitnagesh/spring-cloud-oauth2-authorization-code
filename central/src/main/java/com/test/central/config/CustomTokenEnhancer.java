package com.test.central.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.test.central.document.User;
import com.test.central.model.AuthenticationDetails;

public class CustomTokenEnhancer implements TokenEnhancer {

	private static final String ERROR = "error";

	private static final String NOT_AUTHORIZED = "User is not Authorised";

	private static final String ADDITIONAL_INFO_FIRSTNAME = "firstName";

	private static final String ADDITIONAL_INFO_LASTNAME = "lastName";

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if (authentication != null && authentication.getPrincipal() instanceof AuthenticationDetails) {
			final AuthenticationDetails userAuth = (AuthenticationDetails) authentication.getPrincipal();
			if (userAuth != null) {
				final Map<String, Object> additionalInformation = new HashMap<String, Object>();
				final User user = userAuth.getDetail();
				if (user != null) {
					additionalInformation.put(ADDITIONAL_INFO_FIRSTNAME, user.getFirstName());
					additionalInformation.put(ADDITIONAL_INFO_LASTNAME, user.getLastName());
					((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
				} else {
					additionalInformation.put(ERROR, NOT_AUTHORIZED);
					((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
				}
			}
		}
		return accessToken;
	}

}