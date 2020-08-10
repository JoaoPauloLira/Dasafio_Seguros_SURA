package br.com.sura.dto.builder.test;

import br.com.sura.dto.FazerPedidoProdutoItemDto;

public class CriadorFazerPedidoProdutoItemDto {

	private Long idProduto;
	private int quantidade;

	public CriadorFazerPedidoProdutoItemDto idProduto(Long idProduto) {
		this.idProduto = idProduto;
		return this;
	}

	public CriadorFazerPedidoProdutoItemDto quantidade(int quantidade) {
		this.quantidade = quantidade;
		return this;
	}
	
	public FazerPedidoProdutoItemDto builder() {
		return new FazerPedidoProdutoItemDto(idProduto,quantidade);
	}

}
