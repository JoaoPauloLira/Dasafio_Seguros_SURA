package br.com.sura.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.sura.error.CustomException;

@Entity
public class PedidoItens extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@JsonBackReference
	@ManyToOne
	private Pedido pedido;
	@ManyToOne
	private Produto produto;
	private int quantidade;
	private BigDecimal valor;
	private BigDecimal subTotal;

	public PedidoItens(Pedido pedido, Produto produto, int quantidade) {
		verificaStoque(produto, quantidade);

		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = produto.getPreco();
		this.subTotal = new BigDecimal(quantidade).multiply(valor);
	}

	private void verificaStoque(Produto produto, int quantidade) {
		if (!produto.temEstoque(quantidade))
			throw new CustomException(
					"Quantidade do estoque não é o suficiente, Produto: " + produto.getProduto() + ", Estoque atual = " + produto.getQuantidade());
		if (quantidade <= 0)
			throw new CustomException( "A quandidade mínima para realizar um pedido deve ser = 1");
	}

	public PedidoItens() {
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		verificaStoque(this.produto,quantidade);
		this.quantidade = quantidade;
	}

	public BigDecimal getSubTotal() {
		return new BigDecimal(quantidade).multiply(new BigDecimal(produto.getPreco().toString()));
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
}
