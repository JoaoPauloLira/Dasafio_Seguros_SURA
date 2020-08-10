package br.com.sura.dto.builder.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.sura.dto.FazerPedidoDto;
import br.com.sura.dto.FazerPedidoProdutoItemDto;

public class CriadorFazerPedidoDto {
	
	private List<FazerPedidoProdutoItemDto> produtosPedido = new ArrayList<FazerPedidoProdutoItemDto>();

	public CriadorFazerPedidoDto produtosPedido(List<FazerPedidoProdutoItemDto> produtosPedido) {
		this.produtosPedido = produtosPedido;
		return this;
	}
	
	public FazerPedidoDto builder() {
		return new FazerPedidoDto(produtosPedido);
	}
	
	public FazerPedidoDto builderAutomatico() {
		FazerPedidoProdutoItemDto pedidoProdutoItemDto1 = new CriadorFazerPedidoProdutoItemDto().idProduto(1L).quantidade(5).builder();
		FazerPedidoProdutoItemDto pedidoProdutoItemDto2 = new CriadorFazerPedidoProdutoItemDto().idProduto(2L).quantidade(6).builder();
		
		List<FazerPedidoProdutoItemDto> listFazerPedidoProdutoItemDto = Arrays.asList(pedidoProdutoItemDto1,pedidoProdutoItemDto2);
		
		return new CriadorFazerPedidoDto().produtosPedido(listFazerPedidoProdutoItemDto).builder();
	}
	
	
}
