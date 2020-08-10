package br.com.sura.endpoint;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sura.model.Categoria;
import br.com.sura.service.CategoriaService;

@RestController
@RequestMapping("v1")
public class EndPointCategoria {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/categoria")
	@Cacheable(value = "getCategorias")
	public ResponseEntity<List<Categoria>> listarTodasAsCategorias() {
		return ResponseEntity.ok(categoriaService.getCategorias());
	}

	@GetMapping("/categoria/pesquisaPorNome/{nome}")
	public ResponseEntity<List<Categoria>> listarTodasCategoriaPesquisandoPorNomeDaCategoria(
			@PathVariable(required = true) String nome) {
		Optional<List<Categoria>> categoria = categoriaService.getByCategoria(nome);

		if (categoria.isPresent())
			return ResponseEntity.ok(categoria.get());

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/categoria/{id}")
	public ResponseEntity<Categoria> buscaCategoriaPorId(@PathVariable(required = true) Long id) {
		Optional<Categoria> categoria = categoriaService.getByIdCategoria(id);

		if (categoria.isPresent())
			return ResponseEntity.ok(categoria.get());

		return ResponseEntity.notFound().build();
	}
}
