package br.com.sura.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Rua não pode ser vazio")
	private String rua;
	@NotEmpty(message = "Cidade não pode ser vazio")
	private String cidade;
	@NotEmpty(message = "Bairro não pode ser vazio")
	private String bairro;
	@NotEmpty(message = "CEP não pode ser vazio")
	private String cep;
	@NotEmpty(message = "Estado não pode ser vazio")
	private String estado;

	public Endereco(String rua, String cidade, String bairro, String cep, String estado) {
		this.rua = rua;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
		this.estado = estado;
	}
	
	public Endereco() {
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
