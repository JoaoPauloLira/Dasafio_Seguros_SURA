package br.com.sura.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.sura.model.Pedido;
import br.com.sura.model.PedidoItens;
import br.com.sura.model.enums.Sessao;
import br.com.sura.model.enums.Status;

public class PedidoRetornoDto {

	private Long idPedido;
	private Status status;
	private Sessao sessao;
	private List<ProdutoRetornoDto> produtosPedido = new ArrayList<>();

	public PedidoRetornoDto(Pedido pedido) {

		this.idPedido = pedido.getId();
		this.status = pedido.getStatus();
		this.sessao = pedido.getSessao();
		this.produtosPedido = converterPedidoItens(pedido.getListaItens());
	}

	public PedidoRetornoDto() {
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ProdutoRetornoDto> getProdutoRetorno() {
		return produtosPedido;
	}

	public void setProdutoRetorno(List<ProdutoRetornoDto> produtoRetornoDto) {
		this.produtosPedido = produtoRetornoDto;
	}

	public BigDecimal getSubtotalPedido() {
		Optional<BigDecimal> subtotal = produtosPedido.stream()
				.map(x -> x.getPreco().multiply(new BigDecimal(x.getQuantidade()))).reduce(BigDecimal::add);
		if (subtotal.isPresent())
			return subtotal.get();
		return BigDecimal.ZERO;
	}

	private List<ProdutoRetornoDto> converterPedidoItens(List<PedidoItens> list) {
		List<ProdutoRetornoDto> produtoRetornoDto = new ArrayList<ProdutoRetornoDto>();
		list.forEach(x -> produtoRetornoDto.add(new ProdutoRetornoDto(x.getId(), x.getProduto().getId(),
				x.getProduto().getCategoria().getCategoria(), x.getProduto().getProduto(), x.getProduto().getPreco(),
				x.getQuantidade(), x.getProduto().getDescricao(), x.getProduto().getFoto())));
		return produtoRetornoDto;
	}

	public List<PedidoRetornoDto> converter(List<Pedido> list) {
		List<PedidoRetornoDto> lista = new ArrayList<PedidoRetornoDto>();
		list.forEach(x -> lista.add(new PedidoRetornoDto(x)));
		return lista;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

}
