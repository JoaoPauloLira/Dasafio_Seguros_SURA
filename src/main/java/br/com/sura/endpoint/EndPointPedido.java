package br.com.sura.endpoint;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import br.com.sura.dto.FazerPedidoDto;
import br.com.sura.dto.PedidoRetornoDto;
import br.com.sura.model.Pedido;
import br.com.sura.model.enums.Status;
import br.com.sura.service.PedidoItemService;
import br.com.sura.service.PedidoService;

@RestController
@RequestMapping("v1")
public class EndPointPedido {

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private PedidoItemService pedidoItemService;

	@PostMapping("/pedido")
	@Transactional
	public ResponseEntity<PedidoRetornoDto> criarPedido(@RequestBody @Valid FazerPedidoDto pedidoDto,
			@AuthenticationPrincipal UserDetails userDetails) {

		Pedido pedido = pedidoService.criarPedidoEp(pedidoDto, userDetails);

		return ResponseEntity.ok(new PedidoRetornoDto(pedido));

	}

	@GetMapping("/pedido")
	public ResponseEntity<List<PedidoRetornoDto>> listaMeusPedidos(@AuthenticationPrincipal UserDetails userDetails) {

		Optional<List<Pedido>> pedidos = pedidoService.getPedidos(userDetails.getUsername());

		if (pedidos.isPresent())
			return ResponseEntity.ok(new PedidoRetornoDto().converter(pedidos.get()));

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/pedido/{id}")
	public ResponseEntity<PedidoRetornoDto> listaMeusPedidosPesquisandoPorId(@PathVariable Long id,
			@AuthenticationPrincipal UserDetails userDetails) {

		Optional<Pedido> pedido = pedidoService.getPedidoIdAndEmail(id, userDetails.getUsername());

		if (pedido.isPresent()) {
			return ResponseEntity.ok(new PedidoRetornoDto(pedido.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/pedido/{id}")
	@Transactional
	public ResponseEntity<PedidoRetornoDto> adicionarOuAlterarProdutoNoPedido(@PathVariable Long id,
			@RequestBody @Valid FazerPedidoDto pedidoDto, @AuthenticationPrincipal UserDetails userDetails) {

		Optional<Pedido> pedido = pedidoService.getPedidoIdAndEmail(id, userDetails.getUsername());

		if (pedido.isPresent()) {

			if (pedido.get().getStatus() == Status.FINALIZADO)
				return ResponseEntity.status(HttpStatus.FORBIDDEN).eTag("O Pedido já se encontra-se finalizado")
						.build();

			pedidoService.adicionaOuRemoveProdutoNoPedidoEp(pedidoDto, pedido);

			return ResponseEntity.ok(new PedidoRetornoDto(pedido.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/pedido/finalizar/{idPedido}")
	@Transactional
	public ResponseEntity<?> finalizarPedido(@PathVariable Long idPedido,
			@AuthenticationPrincipal UserDetails userDetails) {

		Optional<Pedido> pedido = pedidoService.getPedidoIdAndEmail(idPedido, userDetails.getUsername());
		if (pedido.isPresent()) {

			if (pedido.get().getStatus() == Status.FINALIZADO)
				return ResponseEntity.status(HttpStatus.FORBIDDEN).eTag("O Pedido já se encontra-se finalizado")
						.build();

			pedidoService.finalizarPedido(pedido.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/pedido/cancelarPedidoItem/{idPedido}/{idPedidoItem}")
	@Transactional
	public ResponseEntity<?> cancelarPedidoItem(@PathVariable Long idPedido, @PathVariable Long idPedidoItem,
			@AuthenticationPrincipal UserDetails userDetails) {

		Optional<Pedido> pedido = pedidoService.getPedidoIdAndEmail(idPedido, userDetails.getUsername());
		if (pedido.isPresent()) {

			if (pedido.get().getStatus() == Status.FINALIZADO)
				return ResponseEntity.status(HttpStatus.FORBIDDEN).eTag("O Pedido já se encontra-se finalizado")
						.build();

			if (pedidoItemService.deletePedidoItens(idPedidoItem))
				return ResponseEntity.ok().build();
			else
				return ResponseEntity.notFound().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/pedido/{idPedido}")
	@Transactional
	public ResponseEntity<?> cancelarPedido(@PathVariable Long idPedido,
			@AuthenticationPrincipal UserDetails userDetails) {

		Optional<Pedido> pedido = pedidoService.getPedidoIdAndEmail(idPedido, userDetails.getUsername());
		if (pedido.isPresent()) {
			pedidoService.deletePedido(pedido.get().getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
