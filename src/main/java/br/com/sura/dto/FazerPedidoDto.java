package br.com.sura.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import br.com.sura.model.Pedido;
import br.com.sura.model.PedidoItens;
import br.com.sura.model.Produto;

public class FazerPedidoDto {

	@NotEmpty(message = "Para realizar um pedido adicione no mínimo um produto")
	private List<FazerPedidoProdutoItemDto> produtosPedido = new ArrayList<FazerPedidoProdutoItemDto>();

	public FazerPedidoDto(List<FazerPedidoProdutoItemDto> produtoPedidoDtos) {
		this.produtosPedido = produtoPedidoDtos;
	}

	public FazerPedidoDto() {
	}

	public List<FazerPedidoProdutoItemDto> getProdutosPedido() {
		return produtosPedido;
	}

	public void setProdutosPedido(List<FazerPedidoProdutoItemDto> produtosPedido) {
		this.produtosPedido = produtosPedido;
	}

	public List<PedidoItens> listaNovoPedidoItens(Pedido pedido, List<Produto> listProdutoPedido) {
		List<PedidoItens> itens = new ArrayList<PedidoItens>();

		listProdutoPedido.forEach(x -> itens.add(new PedidoItens(pedido, x, quantidadeAdicionada(x))));

		return itens;
	}

	public void atualizaPedidoItem(Pedido pedido, List<Produto> listProdutoPedido) {
		List<PedidoItens> itens = pedido.getListaItens();

		listProdutoPedido.forEach(x -> {

			int quantidade = quantidadeAdicionada(x);

			if (itemJaExiste(pedido, x)) {
				itens.stream().filter(z -> z.getProduto().getId() == x.getId()).forEach(d -> {
					d.setQuantidade(quantidade);
					d.getProduto().setQuantidadeReservaTira(x.getQuantidadeReserva());
				});
			} else {
				itens.add(new PedidoItens(pedido, x, quantidade));
			}

		});
	}

	public int quantidadeAdicionada(Produto x) {
		return produtosPedido.stream().filter(y -> y.getIdProduto() == x.getId()).findFirst().get().getQuantidade();
	}

	public boolean itemJaExiste(Pedido pedido, Produto x) {
		return pedido.getListaItens().stream().filter(z -> z.getProduto().getId() == x.getId()).findFirst().isPresent();
	}

}
