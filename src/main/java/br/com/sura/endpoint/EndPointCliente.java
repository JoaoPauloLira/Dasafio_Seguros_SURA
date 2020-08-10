package br.com.sura.endpoint;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sura.dto.ClienteDto;
import br.com.sura.model.Cliente;
import br.com.sura.service.ClienteService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1")
public class EndPointCliente {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/cliente")
	public ResponseEntity<List<ClienteDto>> listaTodosOsClientes(){
		return ResponseEntity.ok(ClienteDto.converter(clienteService.getClientes()));
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<ClienteDto> buscarClientePorId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.getByIdClientes(id);
		
		if(cliente.isPresent()) 
			return ResponseEntity.ok(new ClienteDto(cliente.get()));
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/cliente")
	@Transactional
	@ApiOperation("salvaCliente - Precisa esta logado")
	public ResponseEntity<ClienteDto> salvaCliente(@RequestBody @Valid ClienteDto clienteDto, UriComponentsBuilder uriBuilder) {
		Cliente cliente = clienteDto.newCliente();
		
		clienteService.saveCliente(cliente);
		
		URI uri = uriBuilder.path("/v1/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));		
	}
	
	@PutMapping("/cliente/{id}")
	@Transactional
	@ApiOperation("atualizaCliente - Precisa esta logado e ser o proprio cliente para atualizar suas informações")
	public ResponseEntity<ClienteDto> atualizaCliente(@PathVariable Long id,@RequestBody  @Valid ClienteDto clienteDto, @AuthenticationPrincipal UserDetails userDetails) {
		Optional<Cliente> cliente = clienteService.getByIdClientes(id);
		
		if(cliente.isPresent()) {
			if(userDetails.getUsername().equals(cliente.get().getEmail())) {
				Cliente updateCliente = clienteService.updateCliente(cliente.get(),clienteDto);
				return ResponseEntity.ok(new ClienteDto(updateCliente));
			}
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/cliente/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation("removeCliente - Deve ter permissão de Admin")
	public ResponseEntity<ClienteDto> removeCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.getByIdClientes(id);
		
		if(cliente.isPresent()) {
			clienteService.deleteCliente(cliente.get().getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
