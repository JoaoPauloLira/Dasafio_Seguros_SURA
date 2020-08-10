package br.com.sura.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.sura.model.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Long> {
	
	List<Cliente> findByEmail(String email);

	Cliente findByEmailIgnoreCaseContaining(String email);
	
}
