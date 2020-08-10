package br.com.sura.dto.builder.test;

import javax.persistence.ManyToOne;

import br.com.sura.model.Pedido;
import br.com.sura.model.PedidoItens;
import br.com.sura.model.Produto;

public class CriadorPedidoItem {

	private Pedido pedido;
	@ManyToOne
	private Produto produto;
	private int quantidade;

	public CriadorPedidoItem pedido(Pedido pedido) {
		this.pedido = pedido;
		return this;
	}

	public CriadorPedidoItem produto(Produto produto) {
		this.produto = produto;
		return this;
	}

	public CriadorPedidoItem quantidade(int quantidade) {
		this.quantidade = quantidade;
		return this;
	}
	
	
	public PedidoItens builder() {
		PedidoItens pedidoItens = new PedidoItens(pedido, produto, quantidade);
		
		return pedidoItens;
	}

}
