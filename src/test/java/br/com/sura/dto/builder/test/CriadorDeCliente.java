package br.com.sura.dto.builder.test;

import br.com.sura.model.Cliente;
import br.com.sura.model.Endereco;

public class CriadorDeCliente {

	private String nome;
	private String email;
	private String senha;
	private Endereco endereco;

	public CriadorDeCliente nome(String nome) {
		this.nome = nome;
		return this;
	}

	public CriadorDeCliente email(String email) {
		this.email = email;
		return this;
	}

	public CriadorDeCliente senha(String senha) {
		this.senha = senha;
		return this;
	}

	public Cliente builder() {
		endereco = new Endereco("Rua teste", "Cidade teste", "Bairro teste", "55000-000", "pe");
		return new Cliente(nome, email, senha, endereco);
	}

}
