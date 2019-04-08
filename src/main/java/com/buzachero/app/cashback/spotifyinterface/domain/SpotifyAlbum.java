package com.buzachero.app.cashback.spotifyinterface.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyAlbum {
	
	private String nome;
	private String[] generos;
	private String tipo;

	

}
