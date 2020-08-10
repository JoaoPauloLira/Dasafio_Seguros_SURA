package br.com.sura.Services.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import br.com.sura.dto.FazerPedidoDto;
import br.com.sura.dto.builder.test.CriadorFazerPedidoDto;
import br.com.sura.model.Pedido;
import br.com.sura.service.PedidoService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PedidoServiceTest {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		restTemplate = restTemplate.withBasicAuth("joao.p.lira@gmail.com", "123456");
		FazerPedidoDto fazerPedidoDto = new CriadorFazerPedidoDto().builderAutomatico();
		restTemplate.postForEntity("/v1/pedido", fazerPedidoDto, String.class);
	}

	@Test
	public void deveRetornaUmPedidoPeloId() {
		Optional<Pedido> pedido = pedidoService.getByIdPedido(1L);
		assertTrue(pedido.isPresent());
	}
	
	@Test
	@Transactional
	public void deveRetornarTrueAoDeletarUmPedido() {
		Optional<Pedido> pedido = pedidoService.getByIdPedido(1L);
		boolean deletePedido = pedidoService.deletePedido(pedido.get().getId());
		assertTrue(deletePedido);
	}
	
	@Test
	public void deveRetornarFalseAoTentarDeletarUmPedidoInexistente() {
		boolean deletePedido = pedidoService.deletePedido(5L);
		assertFalse(deletePedido);
	}
}
