package br.com.sura.dto.builder.test;

import java.math.BigDecimal;

import br.com.sura.model.Categoria;
import br.com.sura.model.Produto;

public class CriadorDeProduto {

	private Long idProduto;
	private String produto;
	private BigDecimal preco;
	private Integer quantidade;
	private String descricao;

	public CriadorDeProduto produto(String produto) {
		this.produto = produto;
		return this;
	}

	public CriadorDeProduto preco(BigDecimal preco) {
		this.preco = preco;
		return this;
	}

	public CriadorDeProduto quantidade(Integer quantidade) {
		this.quantidade = quantidade;
		return this;
	}

	public CriadorDeProduto descricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public CriadorDeProduto idProduto(Long idProduto) {
		this.idProduto = idProduto;
		return this;
	}

	public Produto builder() {
		Produto p = new Produto(new Categoria(), produto, preco, quantidade, descricao, null);
		p.setId(idProduto);
		return p;
	}

}
