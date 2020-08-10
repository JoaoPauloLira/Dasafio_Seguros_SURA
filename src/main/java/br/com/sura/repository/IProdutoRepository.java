package br.com.sura.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.sura.model.Produto;

public interface IProdutoRepository extends CrudRepository<Produto, Long> {

}
