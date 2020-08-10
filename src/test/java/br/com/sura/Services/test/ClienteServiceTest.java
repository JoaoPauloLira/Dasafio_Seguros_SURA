package br.com.sura.Services.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.sura.dto.ClienteDto;
import br.com.sura.dto.builder.test.CriadorDeCliente;
import br.com.sura.dto.builder.test.CriadorDeClienteDto;
import br.com.sura.error.CustomException;
import br.com.sura.model.Cliente;
import br.com.sura.service.ClienteService;

@SpringBootTest
public class ClienteServiceTest {

	@Autowired
	private ClienteService clienteService;

	@Test
	public void deveSalvarUmCliente() {
		Cliente cliente = new CriadorDeCliente().nome("Cliente teste 1").email("email@teste.com").senha("123456")
				.builder();

		clienteService.saveCliente(cliente);

		assertNotNull(cliente.getId());
		assertEquals("Cliente teste 1", cliente.getNome());
		assertEquals("email@teste.com", cliente.getEmail());
	}

	@Test
	public void deveDeletarUmCliente() {
		Cliente cliente = clienteService.getByIdClientes(1L).get();

		clienteService.deleteCliente(cliente.getId());
		boolean clienteExcluido = clienteService.getByIdClientes(cliente.getId()).isPresent();

		assertFalse(clienteExcluido);
	}

	@Test
	public void deveRetornaUmErroAoDeletarUmCliente() {
		assertThrows("Erro ao tentar excluir o Cliente ", CustomException.class, () -> {
			clienteService.deleteCliente(88L);
		});
	}

	@Test
	public void deveAtualizarUmCliente() {
		Cliente cliente = clienteService.getByIdClientes(4L).get();
		ClienteDto clienteDto = new CriadorDeClienteDto().bairro("Rua teste").cep("55130-000").cidade("Sanka")
				.senha("558877").email("emailteste@teste.com").estado("PE").nome("Nome teste").builder();

		clienteService.updateCliente(cliente, clienteDto);
		Cliente clienteAtualizado = clienteService.getByIdClientes(4L).get();

		assertEquals("Sanka", clienteAtualizado.getEndereco().getCidade());
		assertEquals("emailteste@teste.com", clienteAtualizado.getEmail());

	}

	@Test
	public void deveRetornaUmErroSeOEmailForInvalido() {

		Cliente cliente = clienteService.getByIdClientes(2L).get();
		ClienteDto clienteDto = new CriadorDeClienteDto().bairro("Rua teste").cep("55130-000").cidade("Sanka")
				.senha("558877").email("elainegmail.com").estado("PE").nome("Nome teste").builder();

		assertThrows("Por favor informe um e-mail valido", CustomException.class, () -> {
			clienteService.updateCliente(cliente, clienteDto);
		});
	}

}
