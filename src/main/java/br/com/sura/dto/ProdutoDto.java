package br.com.sura.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sura.model.Categoria;
import br.com.sura.model.Produto;

public class ProdutoDto {

	@NotNull(message = "O id da categoria não pode ser vazio")
	private Long idCategoria;
	@NotEmpty(message = "O produto não pode ser vazio")
	private String produto;
	@NotNull(message = "O preço não pode ser vazio")
	private BigDecimal preco;
	@NotNull(message = "A quantidade não pode ser vazio")
	private Integer quantidade;
	@NotEmpty(message = "A descrição detalhada não pode ser vazio")
	private String descricao;
	@Lob
	private byte[] foto;
	
	public ProdutoDto() {
	}
	
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
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
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	
	@JsonIgnore
	public List<Produto> converter(Iterable<Produto> produtos) {
		List<Produto> list = new ArrayList<Produto>();
		produtos.forEach(list::add);
		return list;
	}
	public Produto newproduto(Categoria categoria) {
		return new Produto(categoria,produto,preco,quantidade,descricao,foto);
	}
	
	
}
