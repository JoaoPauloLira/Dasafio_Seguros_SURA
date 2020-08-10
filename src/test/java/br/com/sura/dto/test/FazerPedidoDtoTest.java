package br.com.sura.dto.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.sura.dto.FazerPedidoDto;
import br.com.sura.dto.FazerPedidoProdutoItemDto;
import br.com.sura.dto.builder.test.CriadorDePedido;
import br.com.sura.dto.builder.test.CriadorDeProduto;
import br.com.sura.dto.builder.test.CriadorFazerPedidoDto;
import br.com.sura.dto.builder.test.CriadorFazerPedidoProdutoItemDto;
import br.com.sura.dto.builder.test.CriadorPedidoItem;
import br.com.sura.model.Pedido;
import br.com.sura.model.PedidoItens;
import br.com.sura.model.Produto;

public class FazerPedidoDtoTest {

	@Test
	public void deveRetornaQuantidadeAdicionadaNoItemProduto() {

		FazerPedidoProdutoItemDto pedidoProdutoItemDto1 = new CriadorFazerPedidoProdutoItemDto().idProduto(1L)
				.quantidade(5).builder();
		FazerPedidoProdutoItemDto pedidoProdutoItemDto2 = new CriadorFazerPedidoProdutoItemDto().idProduto(2L)
				.quantidade(6).builder();

		List<FazerPedidoProdutoItemDto> listFazerPedidoProdutoItemDto = Arrays.asList(pedidoProdutoItemDto1,
				pedidoProdutoItemDto2);

		FazerPedidoDto fazerPedidoDto = new CriadorFazerPedidoDto().produtosPedido(listFazerPedidoProdutoItemDto)
				.builder();

		Produto produto = new CriadorDeProduto().idProduto(1L).quantidade(10).builder();

		assertEquals(5, fazerPedidoDto.quantidadeAdicionada(produto));

	}

	@Test
	public void deveVerificaSeExisteProdutoNoPedido() {

		Produto produto1 = new CriadorDeProduto().idProduto(1L).quantidade(10).preco(new BigDecimal(2)).builder();
		Produto produto2 = new CriadorDeProduto().idProduto(2L).quantidade(10).preco(new BigDecimal(5)).builder();

		PedidoItens pedidoItens1 = new CriadorPedidoItem().produto(produto1).quantidade(2).builder();

		List<PedidoItens> listaItens = Arrays.asList(pedidoItens1);

		Pedido pedido = new CriadorDePedido().listaItens(listaItens).builder();

		FazerPedidoDto fazerPedidoDto = new CriadorFazerPedidoDto().builder();

		assertTrue(fazerPedidoDto.itemJaExiste(pedido, produto1));
		assertFalse(fazerPedidoDto.itemJaExiste(pedido, produto2));

	}

	@Test
	public void deveAtualizarListaDeItensDoPedido() {

		Produto produtoExistente = new CriadorDeProduto().idProduto(1L).quantidade(10).preco(new BigDecimal(2))
				.builder();
		Produto produtoNovo = new CriadorDeProduto().idProduto(2L).quantidade(10).preco(new BigDecimal(5)).builder();

		PedidoItens pedidoItens = new CriadorPedidoItem().produto(produtoExistente).quantidade(2).builder();

		List<PedidoItens> listaItens = Arrays.asList(pedidoItens);

		Pedido pedido = new CriadorDePedido().listaItens(listaItens).builder();

		List<Produto> listProdutoPedido = Arrays.asList(produtoExistente, produtoNovo);

		FazerPedidoProdutoItemDto pedidoProdutoItemDto1 = new CriadorFazerPedidoProdutoItemDto().idProduto(1L)
				.quantidade(5).builder();
		FazerPedidoProdutoItemDto pedidoProdutoItemDto2 = new CriadorFazerPedidoProdutoItemDto().idProduto(2L)
				.quantidade(6).builder();

		List<FazerPedidoProdutoItemDto> listFazerPedidoProdutoItemDto = Arrays.asList(pedidoProdutoItemDto1,
				pedidoProdutoItemDto2);

		FazerPedidoDto fazerPedidoDto = new CriadorFazerPedidoDto().produtosPedido(listFazerPedidoProdutoItemDto)
				.builder();

		fazerPedidoDto.atualizaPedidoItem(pedido, listProdutoPedido);

		assertEquals(2, pedido.getListaItens().size());

	}

}
