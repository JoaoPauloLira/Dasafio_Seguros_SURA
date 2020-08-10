package br.com.sura.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.sura.model.PedidoItens;

public interface IPedidoItemRepository extends CrudRepository<PedidoItens, Long>{

}
