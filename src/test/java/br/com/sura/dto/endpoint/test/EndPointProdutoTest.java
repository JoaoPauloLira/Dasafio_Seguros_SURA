package br.com.sura.dto.endpoint.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.sura.dto.ProdutoDto;
import br.com.sura.dto.builder.test.CriadorDeProdutoDao;
import br.com.sura.model.Produto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndPointProdutoTest {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;

	@Test
	public void deveRetornarUmJsonDosProdutosRetornandoUmCodeOk() {

		ResponseEntity<String> response = restTemplate.getForEntity("/v1/produto", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void deveRetornaStatusOkParaABuscaDeUmProdutoPeloId() {
		ResponseEntity<Produto> response = restTemplate.getForEntity("/v1/produto/{id}", Produto.class, 1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void deveSalvarUmProdutoQuandoUsuarioAdminEstiverLogado() {

		restTemplate = restTemplate.withBasicAuth("joao.p.lira@gmail.com", "123456");

		ProdutoDto produtoDto = new CriadorDeProdutoDao().descricao("Desc Produto teste").idCategoria(1L)
				.preco(new BigDecimal(4)).produto("Produto teste").quantidade(5).builder();

		ResponseEntity<String> response = restTemplate.postForEntity("/v1/produto/", produtoDto, String.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void deveBloquearOSalvardeUmProdutoQuandoNaoForumUsuarioAdmin() {

		restTemplate = restTemplate.withBasicAuth("elaine@gmail.com", "123456");

		ProdutoDto produtoDto = new CriadorDeProdutoDao().descricao("Desc Produto teste").idCategoria(1L)
				.preco(new BigDecimal(4)).produto("Produto teste").quantidade(5).builder();

		ResponseEntity<String> response = restTemplate.postForEntity("/v1/produto/", produtoDto, String.class);

		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	public void deveReclamarQueONomeDoProdutoNaoFoiInformado() {

		restTemplate = restTemplate.withBasicAuth("joao.p.lira@gmail.com", "123456");

		ProdutoDto produtoDto = new CriadorDeProdutoDao().produto("").descricao("Desc Produto teste").idCategoria(1L)
				.preco(new BigDecimal(4)).quantidade(5).builder();

		ResponseEntity<String> response = restTemplate.postForEntity("/v1/produto/", produtoDto, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertThat(response.getBody()).contains("message", "O produto não pode ser vazio");

	}

}
