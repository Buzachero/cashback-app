package com.buzachero.app.cashback.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.buzachero.app.cashback.AbstractTest;
import com.buzachero.app.cashback.domain.Disco;
import com.buzachero.app.cashback.domain.enums.GeneroDisco;

@Profile("test")
public class DiscoControllerTest extends AbstractTest {
	private static final String NOT_MATCHED = "%s diferentes!";
	private static final String DISCO_ID = "Disco Id";
	private static final String GENERO = "Genero";
	private static final String NOME_DISCO = "Nome disco";
	
	@Before
	public void initializeMvc() {
		super.setUp();
	}
	
	private void assertDisco(Disco expectedDisco, Disco actualDisco) {
		if(expectedDisco == null) {
			throw new IllegalArgumentException("expectedDisco cannot be empty");
		}		
		
		assertEquals(String.format(NOT_MATCHED, DISCO_ID), 
						expectedDisco.getId(), 
						actualDisco.getId());

		assertEquals(String.format(NOT_MATCHED, GENERO), 
						expectedDisco.getGenero(), 
						actualDisco.getGenero());
		
		assertEquals(String.format(NOT_MATCHED, NOME_DISCO), 
						expectedDisco.getNome(), 
						actualDisco.getNome());
	}

	@Test
	public void testFind_Disco1_POP() throws Exception {
		String uri = "/discos/1";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Disco actualDisco = super.mapFromJson(content,  Disco.class);
		
		Disco expectedDisco = new Disco(1, "Disco 1", GeneroDisco.POP, 0.0);

		assertNotNull(actualDisco);
		assertDisco(expectedDisco, actualDisco);		
	}
	
	@Test
	public void testFind_Disco51_MPB() throws Exception {
		String uri = "/discos/51";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Disco actualDisco = super.mapFromJson(content,  Disco.class);
		
		Disco expectedDisco = new Disco(51, "Disco 51", GeneroDisco.MPB, 0.0);

		assertNotNull(actualDisco);
		assertDisco(expectedDisco, actualDisco);		
	}
	
	@Test
	public void testFind_Disco101_CLASSIC() throws Exception {
		String uri = "/discos/101";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Disco actualDisco = super.mapFromJson(content,  Disco.class);
		
		Disco expectedDisco = new Disco(101, "Disco 101", GeneroDisco.CLASSIC, 0.0);

		assertNotNull(actualDisco);
		assertDisco(expectedDisco, actualDisco);		
	}

	@Test
	public void testFind_Disco151_ROCK() throws Exception {
		String uri = "/discos/151";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Disco actualDisco = super.mapFromJson(content,  Disco.class);
		
		Disco expectedDisco = new Disco(151, "Disco 151", GeneroDisco.ROCK, 0.0);

		assertNotNull(actualDisco);
		assertDisco(expectedDisco, actualDisco);		
	}
	
	@Test
	public void testFindPageByGenero() {		
		fail("Not yet implemented");
	}

}
