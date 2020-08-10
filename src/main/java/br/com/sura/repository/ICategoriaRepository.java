package br.com.sura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.sura.model.Categoria;

public interface ICategoriaRepository extends CrudRepository<Categoria, Long> {

	Optional<List<Categoria>> findByCategoriaIgnoreCaseContaining(String categoria);

}
