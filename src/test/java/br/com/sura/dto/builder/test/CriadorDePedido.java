package br.com.sura.dto.builder.test;

import java.util.ArrayList;
import java.util.List;

import br.com.sura.model.Cliente;
import br.com.sura.model.Pedido;
import br.com.sura.model.PedidoItens;
import br.com.sura.model.enums.Sessao;

public class CriadorDePedido {

	private Cliente cliente;
	private Sessao sessao;
	private List<PedidoItens> listaItens = new ArrayList<PedidoItens>();

	public CriadorDePedido cliente(Cliente cliente) {
		this.cliente = cliente;
		return this;
	}

	public CriadorDePedido sessao(Sessao sessao) {
		this.sessao = sessao;
		return this;
	}

	public CriadorDePedido listaItens(List<PedidoItens> listaItens) {
		this.listaItens = listaItens;
		return this;
	}

	public Pedido builder() {
		Pedido pedido = new Pedido(cliente, sessao);
		pedido.setListaItens(listaItens);
		return pedido;
	}

}
