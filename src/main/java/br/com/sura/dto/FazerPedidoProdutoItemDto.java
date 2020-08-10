package br.com.sura.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FazerPedidoProdutoItemDto {

	@NotNull(message = "O Id do Produto  não pode ser vazio")
	private Long idProduto;
	@Min(value = 1L,  message = "A quandidade mínima para realizar um pedido deve ser = 1")
	private int quantidade;

	public FazerPedidoProdutoItemDto() {

	}

	public FazerPedidoProdutoItemDto(Long idProduto, int quantidade) {
		this.idProduto = idProduto;
		this.setQuantidade(quantidade);
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
