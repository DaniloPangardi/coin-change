package br.com.desafio.dto.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExchangeTest {
	
	@Test
	void testBuildExchangeWhenExchangeValueIsTen() throws Exception {
		var exchange = Exchange.buildExchange(10);
		assertEquals(3, exchange.getOptions().size());
	}
	
	@Test
	void testBuildExchangeWhenExchangeValueIsZero() throws Exception {
		var exchange = Exchange.buildExchange(0);
		assertEquals(0, exchange.getOptions().get(0).getCoinQuantity());
	}
	
	@Test
	void testBuildExchangeWhenExchangeValueIsNegative() throws Exception {
		var exchange = Exchange.buildExchange(-1);
		assertTrue(exchange.getOptions().isEmpty());
	}

}
