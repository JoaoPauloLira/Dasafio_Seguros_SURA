package br.com.sura.dto;

import java.math.BigDecimal;

import javax.persistence.Lob;

public class ProdutoRetornoDto {

	private Long idProdutoItem;
	private Long idProduto;
	private String produto;
	private String categoria;
	private BigDecimal preco;
	private Integer quantidade;
	private String descricao;
	@Lob
	private byte[] foto;

	public ProdutoRetornoDto(Long idProdutoItem, Long idProduto, String categoria, String produto, BigDecimal preco,
			Integer quantidade, String descricao, byte[] foto) {
		this.idProdutoItem = idProdutoItem;
		this.idProduto = idProduto;
		this.categoria = categoria;
		this.produto = produto;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.foto = foto;
	}

	public ProdutoRetornoDto() {
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public BigDecimal getSubtotal() {
		return new BigDecimal(quantidade).multiply(preco);
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getIdProdutoItem() {
		return idProdutoItem;
	}

	public void setIdProdutoItem(Long idProdutoItem) {
		this.idProdutoItem = idProdutoItem;
	}

}
