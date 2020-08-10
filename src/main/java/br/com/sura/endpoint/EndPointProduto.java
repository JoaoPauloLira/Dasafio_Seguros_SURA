package br.com.sura.endpoint;

import static br.com.sura.util.Utils.isNull;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sura.dto.ProdutoDto;
import br.com.sura.model.Produto;
import br.com.sura.service.ProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1")
public class EndPointProduto {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/produto")
	public ResponseEntity<List<Produto>> listaTodosOsProdutos() {
		return ResponseEntity.ok(new ProdutoDto().converter(produtoService.getProdutos()));
	}

	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
		Produto produto = produtoService.getByIdProdutos(id);

		if (produto != isNull())
			return ResponseEntity.ok(produto);

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/produto")
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation("salvarProduto - Deve ter permissão de Admin")
	public ResponseEntity<Produto> salvarProduto(@RequestBody @Valid ProdutoDto produtoDto,
			UriComponentsBuilder uriBuilder) {

		Produto produto = produtoService.salvaProdutoEp(produtoDto);

		URI uri = uriBuilder.path("/v1/produto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto);
	}

	

	@PutMapping("/produto/{id}")
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation("atualizarProduto - Deve ter permissão de Admin")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoDto produtoDto) {

		Produto produto = produtoService.validaAtualizacao(id, produtoDto);

		Produto updateproduto = produtoService.updateProduto(produto);
		return ResponseEntity.ok(updateproduto);
	}

	@DeleteMapping("/produto/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation("excluirProduto - Deve ter permissão de Admin")
	public ResponseEntity<ProdutoDto> excluirProduto(@PathVariable Long id) {
		Produto produto = produtoService.getByIdProdutos(id);

		if (produto != isNull()) {
			produtoService.deleteProduto(produto.getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
