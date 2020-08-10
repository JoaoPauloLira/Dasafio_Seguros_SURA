package br.com.sura.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.sura.dto.FazerPedidoDto;
import br.com.sura.model.Cliente;
import br.com.sura.model.Pedido;
import br.com.sura.model.PedidoItens;
import br.com.sura.model.Produto;
import br.com.sura.model.enums.Sessao;
import br.com.sura.model.enums.Status;
import br.com.sura.repository.IPedidoRepository;
import br.com.sura.repository.IProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private IPedidoRepository repository;
	@Autowired
	private IProdutoRepository produtoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ProdutoService produtoService;
	
	public Optional<List<Pedido>> getPedidos(String email) {
		return repository.findByClienteEmail(email);
	}

	public Optional<Pedido> getByIdPedido(Long id) {
		return repository.findById(id);
	}

	public Optional<Pedido> getPedidoIdAndEmail(Long id, String email) {
		return repository.findByPedidoAndCliente(id, email);
	}

	public Pedido savePedido(Pedido pedido) {
		return repository.save(pedido);
	}

	public boolean deletePedido(Long id) {
		Optional<Pedido> pedido = getByIdPedido(id);

		if (!pedido.isPresent())
			return false;

		Map<String, Integer> listaProdutos = listaProdutosPedido(pedido.get());

		if (!atualizaValorResevaProduto(listaProdutos))
			return false;

		repository.deleteById(id);
		return true;
	}

	private Map<String, Integer> listaProdutosPedido(Pedido pedido) {
		Map<String, Integer> listaProdutos = new HashMap<String, Integer>();
		pedido.getListaItens()
				.forEach(x -> listaProdutos.put(String.valueOf(x.getProduto().getId()), x.getQuantidade()));
		return listaProdutos;
	}

	private boolean atualizaValorResevaProduto(Map<String, Integer> listaProdutos) {

		listaProdutos.keySet().forEach(x -> {
			Optional<Produto> produto = produtoRepository.findById(Long.valueOf(x));
			produto.get().setQuantidadeReservaTira(listaProdutos.get(x));
			produtoRepository.save(produto.get());
		});

		return true;
	}
	
	private boolean atualizaQuantidadeProdutoFinalizado(Map<String, Integer> listaProdutos) {

		listaProdutos.keySet().forEach(x -> {
			Optional<Produto> produto = produtoRepository.findById(Long.valueOf(x));
			produto.get().setQuantidadeReservaTira(listaProdutos.get(x));
			produto.get().setNovaQuantidade(listaProdutos.get(x));
			produtoRepository.save(produto.get());
		});

		return true;
	}

	public Pedido updatePedido(Pedido pedido) {
		return repository.save(pedido);
	}
	
	public boolean finalizarPedido(Pedido pedido) {
		
		pedido.setStatus(Status.FINALIZADO);
		Map<String, Integer> listaProdutos = listaProdutosPedido(pedido);
		
		if (!atualizaQuantidadeProdutoFinalizado(listaProdutos))
			return false;
		
		updatePedido(pedido);
		return true;
		
	}
	
	public Pedido criarPedidoEp(FazerPedidoDto pedidoDto, UserDetails userDetails) {
		Cliente cliente = clienteService.getByIdEmail(userDetails.getUsername());

		Pedido pedido = new Pedido(cliente, Sessao.PEDIDO_REALIZADO);
		List<Produto> listProdutoPedido = produtoService.getListProduto(pedidoDto);

		List<PedidoItens> listaItens = pedidoDto.listaNovoPedidoItens(pedido, listProdutoPedido);
		pedido.setListaItens(listaItens);

		savePedido(pedido);
		produtoService.getAtualizaQtdReserva(listProdutoPedido, pedidoDto.getProdutosPedido());
		return pedido;
	}
	
	public void adicionaOuRemoveProdutoNoPedidoEp(FazerPedidoDto pedidoDto, Optional<Pedido> pedido) {
		List<Produto> listProdutoPedido = produtoService.getListProduto(pedidoDto);

		pedidoDto.atualizaPedidoItem(pedido.get(), listProdutoPedido);

		savePedido(pedido.get());
		produtoService.getAtualizaQtdReserva(listProdutoPedido, pedidoDto.getProdutosPedido());
	}

}
