package br.com.sura.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sura.error.CustomException;
import br.com.sura.model.Categoria;
import br.com.sura.repository.ICategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private ICategoriaRepository repository;

	public List<Categoria> getCategorias() {
		List<Categoria> listaCategoria = new ArrayList<Categoria>();
		repository.findAll().forEach(listaCategoria::add);
		return listaCategoria;
	}

	public Optional<Categoria> getByIdCategoria(Long id) {
		Optional<Categoria> categoria = repository.findById(id);

		if(!categoria.isPresent()) 
			throw new CustomException("Categoria não existente");
		
		return categoria;
	}
	
	public Optional<List<Categoria>> getByCategoria(String categoria) {
		return repository.findByCategoriaIgnoreCaseContaining(categoria);
	}
	
}

