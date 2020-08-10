package br.com.sura.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Categoria extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String categoria;
	@OneToMany(mappedBy = "categoria")
	private List<Produto> listProdutos = new ArrayList<Produto>();
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	@JsonIgnore
	public void setListProdutos(List<Produto> listProdutos) {
		this.listProdutos = listProdutos;
	}

}
