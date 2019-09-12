package com.buzachero.app.cashback.controller;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.buzachero.app.cashback.AbstractTest;
import com.buzachero.app.cashback.domain.Disco;
import com.buzachero.app.cashback.domain.enums.GeneroDisco;
import com.buzachero.app.cashback.services.exceptions.ObjectNotFoundException;

@ActiveProfiles("test")
public class DiscoControllerTest extends AbstractTest {
	private static final String NOT_MATCHED = "%s diferentes!";
	private static final String DISCO_ID = "Disco Id";
	private static final String GENERO = "Genero";
	private static final String NOME_DISCO = "Nome disco";
	private static final String DISCO_BASE_URL_SUFFIX = "/discos/";
	private Random rand = new Random();
	
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
	public void testFind_Discos_POP() throws Exception {		
		int discoId = rand.nextInt(50) + 1;
		String uri = DISCO_BASE_URL_SUFFIX + discoId;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);
		String content = mvcResult.getResponse().getContentAsString();
		Disco actualDisco = super.mapFromJson(content,  Disco.class);
		
		Disco expectedDisco = new Disco(discoId, "Disco " + discoId, GeneroDisco.POP, 0.0);

		assertNotNull(actualDisco);
		assertDisco(expectedDisco, actualDisco);		
	}
	
	@Test
	public void testFind_Discos_MPB() throws Exception {
		int discoId = rand.nextInt(50) + 51;
		String uri = DISCO_BASE_URL_SUFFIX + discoId;

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);
		String content = mvcResult.getResponse().getContentAsString();
		Disco actualDisco = super.mapFromJson(content,  Disco.class);
		
		Disco expectedDisco = new Disco(discoId, "Disco " + discoId, GeneroDisco.MPB, 0.0);

		assertNotNull(actualDisco);
		assertDisco(expectedDisco, actualDisco);		
	}
	
	@Test
	public void testFind_Discos_CLASSIC() throws Exception {
		int discoId = rand.nextInt(50) + 101;
		String uri = DISCO_BASE_URL_SUFFIX + discoId;

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);
		String content = mvcResult.getResponse().getContentAsString();
		Disco actualDisco = super.mapFromJson(content,  Disco.class);
		
		Disco expectedDisco = new Disco(discoId, "Disco " + discoId, GeneroDisco.CLASSIC, 0.0);

		assertNotNull(actualDisco);
		assertDisco(expectedDisco, actualDisco);		
	}

	@Test
	public void testFind_Discos_ROCK() throws Exception {
		int discoId = rand.nextInt(50) + 151;
		String uri = DISCO_BASE_URL_SUFFIX + discoId;

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);
		String content = mvcResult.getResponse().getContentAsString();
		Disco actualDisco = super.mapFromJson(content,  Disco.class);
		
		Disco expectedDisco = new Disco(discoId, "Disco " + discoId, GeneroDisco.ROCK, 0.0);

		assertNotNull(actualDisco);
		assertDisco(expectedDisco, actualDisco);		
	}
	
	@Test
	public void testFind_Discos_Not_Exist() throws Exception {
		int discoId = rand.nextInt(100) + 201;
		String uri = DISCO_BASE_URL_SUFFIX + discoId;

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.NOT_FOUND.value(), status);
		
		Exception exception = mvcResult.getResolvedException();
		assertTrue(exception instanceof ObjectNotFoundException);		
	}
	
	@Test
	public void testFindPageByGenero() {		
		fail("Not yet implemented");
	}

}
