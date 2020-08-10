package br.com.sura.dto.endpoint.test;

import static org.assertj.core.api.Assertions.assertThat;
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

import br.com.sura.dto.ClienteDto;
import br.com.sura.dto.builder.test.CriadorDeCliente;
import br.com.sura.model.Cliente;
import br.com.sura.model.Produto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndPointClienteTest {

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
	public void deveRetornarUmJsonDosClientesRetornandoUmCodeOk() {

		ResponseEntity<String> response = restTemplate.getForEntity("/v1/cliente", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void deveRetornaStatusOkParaABuscaDeUmClientePeloId() {
		ResponseEntity<Produto> response = restTemplate.getForEntity("/v1/cliente/{id}", Produto.class, 1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void deveSalvarUmCliente() {

		Cliente cliente = new CriadorDeCliente().nome("Cliente teste 1").email("email@teste.com").senha("123456")
				.builder();
		ClienteDto clienteDto = new ClienteDto(cliente);
		clienteDto.setSenha(cliente.getSenha());
		
		ResponseEntity<String> response = restTemplate.postForEntity("/v1/cliente", clienteDto, String.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	public void deveReclamarQueASenhaNaoPodeSerVaziaAoSalvarUmCliente() {

		Cliente cliente = new CriadorDeCliente().nome("Cliente teste 1").email("email@teste.com")
				.builder();
		ClienteDto clienteDto = new ClienteDto(cliente);
		
		ResponseEntity<String> response = restTemplate.postForEntity("/v1/cliente", clienteDto, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertThat(response.getBody()).contains("message", "A Senha não pode ser vazio");
	}

	
	
}
