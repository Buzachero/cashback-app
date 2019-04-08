package com.buzachero.app.cashback.spotifyinterface.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.buzachero.app.cashback.spotifyinterface.domain.SpotifyAuthorizationResponse;
import com.buzachero.app.cashback.spotifyinterface.domain.SpotifyToken;

@Service
@Profile("prod")
public class SpotifyWebApiService {
	
	private static final String BASE_AUTHORIZE_ENDPOINT = "https://accounts.spotify.com/authorize";
	private static final String BASE_TOKEN_ENDPOINT = "https://accounts.spotify.com/api/token";
	
	private static final String GRANT_TYPE = "authorization_code";
	
	@Value("${spotify.client_id}")
	private String clientId;
	@Value("${spotify.redirect_uri}")
	private String redirectURI;	
	
	private SpotifyToken spotifyToken;	

	private static RestTemplate restTemplate = new RestTemplate();
	
	private String getFinalAuthorizeURL() {
		String url = BASE_AUTHORIZE_ENDPOINT
				+ "?client_id=" + clientId;
				//+ "&redirect_uri=" + redirectURI;
		
		return url;		
	}
	
	private String getFinalTokenURL(String code) {
		String url = BASE_TOKEN_ENDPOINT
				+ "?grant_type=" + GRANT_TYPE
				+ "?code=" + code
				+ "&redirect_uri=" + redirectURI;
		
		return url;
	}
	
	private SpotifyToken requestToken(String authorizationCode) {
		return restTemplate.getForObject(getFinalTokenURL(authorizationCode), SpotifyToken.class);		
	}
	
	public String getAccessAuthorizationToken() {
		String finalAuthorizeURL = getFinalAuthorizeURL();
		
		SpotifyAuthorizationResponse authResponse = restTemplate.getForObject(finalAuthorizeURL, SpotifyAuthorizationResponse.class);
		
		System.out.println(authResponse);
		
		if(!authResponse.getCode().isEmpty()) {
			setSpotifyToken(requestToken(authResponse.getCode()));
			System.out.println(getSpotifyToken());
			return spotifyToken.getAccessToken();
		}
		
		return null;
	}	
	
	public SpotifyToken getSpotifyToken() {
		return spotifyToken;
	}

	public void setSpotifyToken(SpotifyToken spotifyToken) {
		this.spotifyToken = spotifyToken;
	}
	
}
