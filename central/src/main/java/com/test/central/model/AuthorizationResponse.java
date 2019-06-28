package com.test.central.model;

import org.springframework.security.oauth2.provider.AuthorizationRequest;

public class AuthorizationResponse {

	private String redirectUri;

	private String code;

	private AuthorizationRequest authorizationRequest;

	private String error;

	public AuthorizationResponse(String redirectUri, String code) {
		super();
		this.redirectUri = redirectUri;
		this.code = code;
	}

	public AuthorizationResponse(String redirectUri, String code, AuthorizationRequest authorizationRequest) {
		super();
		this.redirectUri = redirectUri;
		this.code = code;
		this.authorizationRequest = authorizationRequest;
	}

	public AuthorizationResponse(String error) {
		super();
		this.error = error;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public AuthorizationRequest getAuthorizationRequest() {
		return authorizationRequest;
	}

	public void setAuthorizationRequest(AuthorizationRequest authorizationRequest) {
		this.authorizationRequest = authorizationRequest;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}