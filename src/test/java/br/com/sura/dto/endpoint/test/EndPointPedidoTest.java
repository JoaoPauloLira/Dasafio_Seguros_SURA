package br.com.sura.dto.endpoint.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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

import br.com.sura.dto.FazerPedidoDto;
import br.com.sura.dto.PedidoRetornoDto;
import br.com.sura.dto.builder.test.CriadorDeCliente;
import br.com.sura.dto.builder.test.CriadorDePedido;
import br.com.sura.dto.builder.test.CriadorFazerPedidoDto;
import br.com.sura.model.Cliente;
import br.com.sura.model.Pedido;
import br.com.sura.model.enums.Sessao;
import br.com.sura.repository.IPedidoRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndPointPedidoTest {

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
	public void deveRetornaStatusOkParaABuscaDeUmPedidoPeloId() {
		ResponseEntity<PedidoRetornoDto> response = restTemplate.getForEntity("/v1/pedido/{id}", PedidoRetornoDto.class, 1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void deveRetornarUmCodeNotFoundQuandoNaoHouverPedido() {

		restTemplate = restTemplate.withBasicAuth("elaine@gmail.com", "123456");

		ResponseEntity<String> response = restTemplate.getForEntity("/v1/pedido", String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void deveSalvarUmPedidoQuandoUsuarioEstiverLogado() {

		restTemplate = restTemplate.withBasicAuth("joao.p.lira@gmail.com", "123456");

		FazerPedidoDto fazerPedidoDto = new CriadorFazerPedidoDto().builderAutomatico();

		ResponseEntity<String> response = restTemplate.postForEntity("/v1/pedido", fazerPedidoDto, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void deveRetornarUmJsonDosPedidosRetornandoUmCodeOk() {

		restTemplate = restTemplate.withBasicAuth("joao.p.lira@gmail.com", "123456");

		Cliente cliente = new CriadorDeCliente().nome("Cliente teste 1").email("email@teste.com").senha("123456")
				.builder();
		Pedido pedido = new CriadorDePedido().cliente(cliente).sessao(Sessao.PEDIDO_REALIZADO).builder();

		List<Pedido> asList = Arrays.asList(pedido);

		IPedidoRepository pedidoServiceMock = mock(IPedidoRepository.class);
		when(pedidoServiceMock.findAll()).thenReturn(asList);

		ResponseEntity<String> response = restTemplate.getForEntity("/v1/pedido", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
