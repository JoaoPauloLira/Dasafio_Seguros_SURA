package br.com.sura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.sura.model.Pedido;

public interface IPedidoRepository extends CrudRepository<Pedido, Long> {
	
	Optional<List<Pedido>> findByClienteEmail(String email);
	
	@Query("select p from Pedido p join p.cliente c where p.id = :id and c.email = :email")
	Optional<Pedido> findByPedidoAndCliente(@Param("id") Long id, @Param("email") String email);
	
}
