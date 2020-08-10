package br.com.sura.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.sura.model.enums.Sessao;
import br.com.sura.model.enums.Status;

@Entity
public class Pedido extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private LocalDateTime data = LocalDateTime.now();
	@ManyToOne
	private Cliente cliente;
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;
	@Enumerated(EnumType.STRING)
	private Sessao sessao;

	@JsonManagedReference
	@OneToMany(mappedBy = "pedido", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<PedidoItens> listaItens = new ArrayList<PedidoItens>();

	public Pedido(Cliente cliente, Sessao sessao) {
		this.cliente = cliente;
		this.sessao = sessao;
	}

	public Pedido() {
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public List<PedidoItens> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<PedidoItens> listaItens) {
		this.listaItens.clear();
		this.listaItens.addAll(listaItens);
	}
	

	public void removerItemProduto(PedidoItens item) {
		this.listaItens.remove(item);
	}


}
