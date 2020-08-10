package br.com.sura.dto.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.sura.dto.ClienteDto;
import br.com.sura.dto.builder.test.CriadorDeCliente;
import br.com.sura.model.Cliente;
import br.com.sura.service.ClienteService;

public class ClienteDtoTest {

	@Test
	public void aoReceberUmaListaDeClientesDeveRetornaUmaListaClientesDtoPopuladas() {
		// Arrange
		Cliente cliente1 = new CriadorDeCliente().nome("Cliente teste 1").email("email@teste.com").senha("123456")
				.builder();
		Cliente cliente2 = new CriadorDeCliente().nome("Cliente teste 2").email("email@teste.com").senha("123456")
				.builder();
		Cliente cliente3 = new CriadorDeCliente().nome("Cliente teste 3").email("email@teste.com").senha("123456")
				.builder();
		Iterable<Cliente> list = Arrays.asList(cliente1, cliente2, cliente3);

		ClienteService clienteServiceMock = mock(ClienteService.class);
		when(clienteServiceMock.getClientes()).thenReturn(list);

		// Act
		Iterable<Cliente> listClientes = clienteServiceMock.getClientes();
		List<ClienteDto> converter = ClienteDto.converter(listClientes);

		// Assert
		assertEquals(3, converter.size());
	}

}
