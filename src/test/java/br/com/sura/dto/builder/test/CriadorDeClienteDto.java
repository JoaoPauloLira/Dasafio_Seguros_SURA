package br.com.sura.dto.builder.test;

import br.com.sura.dto.ClienteDto;

public class CriadorDeClienteDto {
	private String nome;
	private String email;
	private String senha;
	private String rua;
	private String cidade;
	private String bairro;
	private String cep;
	private String estado;

	
	public CriadorDeClienteDto nome(String nome) {
		this.nome = nome;
		return this;
	}

	public CriadorDeClienteDto email(String email) {
		this.email = email;
		return this;
	}

	public CriadorDeClienteDto senha(String senha) {
		this.senha = senha;
		return this;
	}

	public CriadorDeClienteDto rua(String rua) {
		this.rua = rua;
		return this;
	}

	public CriadorDeClienteDto cidade(String cidade) {
		this.cidade = cidade;
		return this;
	}

	public CriadorDeClienteDto bairro(String bairro) {
		this.bairro = bairro;
		return this;
	}

	public CriadorDeClienteDto cep(String cep) {
		this.cep = cep;
		return this;
	}

	public CriadorDeClienteDto estado(String estado) {
		this.estado = estado;
		return this;
	}

	public ClienteDto builder() {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setBairro(bairro);
		clienteDto.setCep(cep);
		clienteDto.setCidade(cidade);
		clienteDto.setEmail(email);
		clienteDto.setEstado(estado);
		clienteDto.setNome(nome);
		clienteDto.setRua(rua);
		clienteDto.setSenha(senha);
		return clienteDto;
	}
}
