package br.com.wine.cep.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.wine.cep.domainvalue.Messages;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CepControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void shouldReturnOKStatusOnGetAll() throws Exception {
		
		this.mvc.perform(get("/cep")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldReturnACreatedStatus() throws Exception {
		
		this.mvc.perform(post("/cep")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(Messages.CEP_DTO_STRING))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void shouldReturnOkStatusOnGetCep() throws Exception {
		
		this.mvc.perform(get("/cep/15486125")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldReturnOkOnDeleteCep() throws Exception {
		
		this.mvc.perform(delete("/cep/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldReturnErrorOnDeleteCep() throws Exception {
		
		this.mvc.perform(delete("/cep/100")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}
}
