package br.com.sura.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Cliente extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "O nome não pode ser vazio")
	private String nome;
	@Email(message = "e-mail invalido")
	@NotEmpty(message = "O e-mail não pode ser vazio")
	@Column(unique = true)
	private String email;
	@NotEmpty(message = "A Senha não pode ser vazio")
	private String senha;
	private Endereco endereco;
	private boolean admin;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> listaPedidos = new ArrayList<Pedido>();

	public Cliente(String nome, String email, String senha, Endereco endereco) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
	}
	
	public Cliente() {
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

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	
}
