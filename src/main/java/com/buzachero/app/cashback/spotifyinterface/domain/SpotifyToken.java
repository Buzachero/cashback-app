package com.buzachero.app.cashback.spotifyinterface.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpotifyToken implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
	private String tokenType;
	private String scope;
	@JsonProperty("expires_in")
	private Integer expiresIn;
	@JsonProperty("refresh_token")
	private String refreshToken;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public Integer getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpotifyToken [accessToken=");
		builder.append(accessToken);
		builder.append(", tokenType=");
		builder.append(tokenType);
		builder.append(", scope=");
		builder.append(scope);
		builder.append(", expiresIn=");
		builder.append(expiresIn);
		builder.append(", refreshToken=");
		builder.append(refreshToken);
		builder.append("]");
		return builder.toString();
	}	
}
