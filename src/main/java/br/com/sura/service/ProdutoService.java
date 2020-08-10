package br.com.sura.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sura.dto.FazerPedidoDto;
import br.com.sura.dto.FazerPedidoProdutoItemDto;
import br.com.sura.dto.ProdutoDto;
import br.com.sura.error.CustomException;
import br.com.sura.model.Categoria;
import br.com.sura.model.Produto;
import br.com.sura.repository.IProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private IProdutoRepository produtoRepository;
	@Autowired
	private CategoriaService categoriaService;

	public Iterable<Produto> getProdutos() {
		return produtoRepository.findAll();
	}

	public Produto getByIdProdutos(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new CustomException("O Produto não existe!"));
	}

	public Produto saveProduto(Produto Produto) {

		return produtoRepository.save(Produto);
	}

	public void deleteProduto(Long id) {
		produtoRepository.delete(getByIdProdutos(id));
	}

	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto validaAtualizacao(Long id, ProdutoDto produtoDto) {
		Produto produto = getByIdProdutos(id);
		Categoria categoria = categoriaService.getByIdCategoria(produtoDto.getIdCategoria()).get();
		Produto produtoUpdate = produtoDto.newproduto(categoria);
		produtoUpdate.setId(produto.getId());
		return produtoUpdate;
	}

	public List<Produto> getListProduto(@Valid FazerPedidoDto pedidoDto) {
		List<FazerPedidoProdutoItemDto> produtoPedidoDtos = pedidoDto.getProdutosPedido();
		List<Produto> list = new ArrayList<Produto>();

		produtoPedidoDtos.forEach(x -> list.add(getByIdProdutos(x.getIdProduto())));
		return list;
	}
	
	public void getAtualizaQtdReserva(List<Produto> list, List<FazerPedidoProdutoItemDto> produtoPedidoDtos) {
		list.forEach(x -> x.setQuantidadeReserva(produtoPedidoDtos.stream()
				.filter(z -> z.getIdProduto() == x.getId()).findFirst().get().getQuantidade()));
		
		list.forEach(this::updateProduto);
	}
	
	public Produto salvaProdutoEp(ProdutoDto produtoDto) {
		Categoria categoria = categoriaService.getByIdCategoria(produtoDto.getIdCategoria()).get();
		Produto produto = produtoDto.newproduto(categoria);

		saveProduto(produto);
		return produto;
	}
	
	

}
