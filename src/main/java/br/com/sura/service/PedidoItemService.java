package br.com.sura.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sura.model.PedidoItens;
import br.com.sura.model.Produto;
import br.com.sura.repository.IPedidoItemRepository;
import br.com.sura.repository.IProdutoRepository;

@Service
public class PedidoItemService {

	@Autowired
	private IPedidoItemRepository repository;
	@Autowired
	private IProdutoRepository produtoRepository;

	public Iterable<PedidoItens> getPedidoItens() {
		return repository.findAll();
	}

	public Optional<PedidoItens> getByIdPedidoItens(Long id) {
		return repository.findById(id);
	}

	public PedidoItens savePedidoItens(PedidoItens pedidoItens) {
		return repository.save(pedidoItens);
	}

	public boolean deletePedidoItens(Long id) {
		Optional<PedidoItens> pedidoItens = getByIdPedidoItens(id);

		if (!pedidoItens.isPresent())
			return false;

		int quantidadeDevolvida = pedidoItens.get().getQuantidade();
		Long idProduto = pedidoItens.get().getProduto().getId();

		if (!atualizaValorResevaProduto(idProduto, quantidadeDevolvida))
			return false;

		repository.deleteById(id);
		return true;
	}

	public PedidoItens updatePedidoItens(PedidoItens pedidoItens) {
		return repository.save(pedidoItens);

	}

	private boolean atualizaValorResevaProduto(Long idProduto, int quantidade) {
		Optional<Produto> produto = produtoRepository.findById(idProduto);
		if (!produto.isPresent())
			return false;

		produto.get().setQuantidadeReservaTira(quantidade);
		produtoRepository.save(produto.get());
		return true;
	}

}
