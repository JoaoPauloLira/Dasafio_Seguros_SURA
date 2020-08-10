package br.com.sura.dto;

import static br.com.sura.util.Utils.encryptSenha;
import static br.com.sura.util.Utils.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sura.model.Cliente;
import br.com.sura.model.Endereco;

public class ClienteDto {

	private Long id;
	@NotEmpty(message = "O nome não pode ser vazio")
	private String nome;
	@Email(message = "e-mail invalido")
	@NotEmpty(message = "O e-mail não pode ser vazio")
	private String email;
	@NotEmpty(message = "A Senha não pode ser vazio")
	private String senha;
	@NotEmpty(message = "Rua não pode ser vazio")
	private String rua;
	@NotEmpty(message = "Cidade não pode ser vazio")
	private String cidade;
	@NotEmpty(message = "Bairro não pode ser vazio")
	private String bairro;
	@NotEmpty(message = "CEP não pode ser vazio")
	@Size(min = 8, max = 9, message = "O CEP é inválido.")
	private String cep;
	@NotEmpty(message = "Estado não pode ser vazio")
	private String estado;

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.rua = cliente.getEndereco().getRua();
		this.cidade = cliente.getEndereco().getCidade();
		this.bairro = cliente.getEndereco().getBairro();
		this.cep = cliente.getEndereco().getCep();
		this.estado = cliente.getEndereco().getEstado();
	}

	public ClienteDto() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = isNullOrEmpty(senha) ? null : encryptSenha(senha);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static List<ClienteDto> converter(Iterable<Cliente> listaCliente) {
		List<Cliente> listaPopulada = new ArrayList<>();
		listaCliente.forEach(listaPopulada::add);

		return listaPopulada.stream().map(ClienteDto::new).collect(Collectors.toList());
	}

	@JsonIgnore
	public Cliente newCliente() {
		return new Cliente(nome, email, senha, new Endereco(rua, cidade, bairro, cep, estado));
	}

}
