package br.com.sura.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.sura.dto.builder.test.CriadorDeProduto;
import br.com.sura.dto.builder.test.CriadorPedidoItem;
import br.com.sura.model.PedidoItens;
import br.com.sura.model.Produto;

public class PedidoItensTest {

	
	@Test
	public void deveRetornarSubtotalDoPedidoItem() {
		
		Produto produto = new CriadorDeProduto().idProduto(1L).quantidade(10).preco(new BigDecimal(5)).builder();
	
		PedidoItens pedidoItens = new CriadorPedidoItem().produto(produto).quantidade(2).builder();
		
		assertEquals(new BigDecimal(10),pedidoItens.getSubTotal());
	}
	
}
