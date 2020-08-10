package br.com.sura.dto.builder.test;

import java.math.BigDecimal;

import br.com.sura.dto.ProdutoDto;

public class CriadorDeProdutoDao {

	private Long idCategoria;
	private String produto;
	private BigDecimal preco;
	private Integer quantidade;
	private String descricao;
	private byte[] foto;

	public CriadorDeProdutoDao idCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
		return this;
	}

	public CriadorDeProdutoDao produto(String produto) {
		this.produto = produto;
		return this;
	}

	public CriadorDeProdutoDao preco(BigDecimal preco) {
		this.preco = preco;
		return this;
	}

	public CriadorDeProdutoDao quantidade(Integer quantidade) {
		this.quantidade = quantidade;
		return this;
	}

	public CriadorDeProdutoDao descricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public CriadorDeProdutoDao foto(byte[] foto) {
		this.foto = foto;
		return this;
	}

	

	public ProdutoDto builder() {
		ProdutoDto produtoDto = new ProdutoDto();
		
		produtoDto.setDescricao(descricao);
		produtoDto.setFoto(foto);
		produtoDto.setIdCategoria(idCategoria);
		produtoDto.setPreco(preco);
		produtoDto.setProduto(produto);
		produtoDto.setQuantidade(quantidade);
		
		return produtoDto;
	}

	
}
