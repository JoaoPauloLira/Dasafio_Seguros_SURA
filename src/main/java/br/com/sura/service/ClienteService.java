package br.com.sura.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sura.dto.ClienteDto;
import br.com.sura.error.CustomException;
import br.com.sura.model.Cliente;
import br.com.sura.repository.IClienteRepository;
import br.com.sura.util.Utils;

@Service
public class ClienteService {

	@Autowired
	private IClienteRepository repository;

	public Iterable<Cliente> getClientes() {
		return repository.findAll();
	}

	public Optional<Cliente> getByIdClientes(Long id) {
		return repository.findById(id);
	}

	public Cliente getByIdEmail(String email) {
		return repository.findByEmailIgnoreCaseContaining(email);
	}
	
	public Cliente saveCliente(Cliente cliente) {

		if (!repository.findByEmail(cliente.getEmail()).isEmpty())
			throw new CustomException("E-mail já está sendo utilizado por outro Cliente");

		return repository.save(cliente);
	}

	public boolean deleteCliente(Long id) {

		Optional<Cliente> clienteResult = getByIdClientes(id);
		if (!clienteResult.isEmpty()) {
			repository.delete(clienteResult.get());
			return true;
		}
		throw new CustomException("Erro ao tentar excluir o Cliente ");
	}

	public Cliente updateCliente(Cliente cliente, ClienteDto clienteDto) {

		boolean emailExiste = repository.findByEmail(clienteDto.getEmail()).stream().anyMatch(x -> {
			return x.getId() != cliente.getId();
		});

		if (emailExiste)
			throw new CustomException("E-mail já está sendo utilizado por outro Cliente");

		if (!Utils.isValidEmailAddressRegex(clienteDto.getEmail()))
			throw new CustomException("Por favor informe um e-mail valido");

		Cliente clienteUpdate = clienteDto.newCliente();
		clienteUpdate.setId(cliente.getId());

		return repository.save(clienteUpdate);

	}
}
