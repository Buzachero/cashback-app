package com.buzachero.app.cashback.spotifyinterface.domain;

import java.io.Serializable;

public class SpotifyAuthorizationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String state;
	private String error;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpotifyAuthorizationResponse [code=");
		builder.append(code);
		builder.append(", state=");
		builder.append(state);
		builder.append(", error=");
		builder.append(error);
		builder.append("]");
		return builder.toString();
	}
	
	
}
