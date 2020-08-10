package br.com.sura.Services.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.sura.dto.ProdutoDto;
import br.com.sura.dto.builder.test.CriadorDeProdutoDao;
import br.com.sura.error.CustomException;
import br.com.sura.model.Produto;
import br.com.sura.service.ProdutoService;

@SpringBootTest
public class ProdutoServiceTest {

	@Autowired
	private ProdutoService produtoService;
	
	@Test
	public void aoDeleterUmProdutoEPesquisarDeveRetornarUmaExcecao() {
		Produto produto = produtoService.getByIdProdutos(10L);

		produtoService.deleteProduto(produto.getId());
		
		assertThrows("O Produto não existe!", CustomException.class, () -> {
			produtoService.getByIdProdutos(10L);
		});
	}
	
	@Test
	public void deveVerificarserOProdutoDtoEstaValido() {

		ProdutoDto produtoDto = new CriadorDeProdutoDao().descricao("Desc Produto teste").idCategoria(1L)
				.preco(new BigDecimal(4)).produto("Produto teste").quantidade(5).builder();

		Produto validaAtualizacao = produtoService.validaAtualizacao(5L, produtoDto);
		
		assertEquals("Desc Produto teste", validaAtualizacao.getDescricao());
	}
	
}
