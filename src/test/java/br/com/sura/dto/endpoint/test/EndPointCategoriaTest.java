package br.com.sura.dto.endpoint.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.sura.model.Categoria;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndPointCategoriaTest {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;

	@TestConfiguration
	static class Config {
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthentication("joao.p.lira@gmail.com", "123456");
		}
	}
	
	@Test
	public void deveRetornarUmJsonDasCategoriaRetornandoUmCodeOk() {

		ResponseEntity<String> response = restTemplate.getForEntity("/v1/categoria", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void deveRetornaStatusOkParaABuscaDeUmaCategoriaPeloId() {
		ResponseEntity<Categoria> response = restTemplate.getForEntity("/v1/categoria/{id}", Categoria.class, 1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void deveRetornaStatusOkParaABuscaDeUmaCategoriaPeloNome() {
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/categoria/pesquisaPorNome/{name}", String.class, "Alimento");
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}
