package br.com.sura.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Categoria categoria;
	private String produto;
	private BigDecimal preco;
	private Integer quantidade;
	@Column(columnDefinition = "INTEGER default 0")
	private Integer quantidadeReserva = 0;
	private String descricao;
	@Lob
	private byte[] foto;

	public Produto(Categoria categoria, String produto, BigDecimal preco, Integer quantidade, String descricao,
			byte[] foto) {
		this.categoria = categoria;
		this.produto = produto;
		this.preco = preco;
		this.quantidade = quantidade;
		this.quantidadeReserva = 0;
		this.descricao = descricao;
		this.foto = foto;
	}

	public Produto() {
	}

	@OneToMany(mappedBy = "produto")
	@JsonIgnore
	private List<PedidoItens> pedidoItens = new ArrayList<PedidoItens>();

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
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
		return quantidade - quantidadeReserva;
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

	public List<PedidoItens> getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(List<PedidoItens> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}

	public boolean temEstoque(int qtd) {
		return getQuantidade() >= qtd;
	}

	public Integer getQuantidadeReserva() {
		return quantidadeReserva;
	}

	public void setQuantidadeReserva(Integer quantidadeReserva) {
		this.quantidadeReserva = quantidadeReserva + getQuantidadeReserva();
	}
	
	public void setQuantidadeReservaTira(Integer quantidadeReserva) {
		this.quantidadeReserva = quantidadeReserva - getQuantidadeReserva();
	}

	public void setNovaQuantidade(Integer quantidadeVendida) {
		this.quantidade = this.quantidade - quantidadeVendida;
	}
	
}
