package br.com.desafio.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.desafio.dto.request.PaymentRequest;

public class PaymentTest {
	
	private static final Long PLAYER_ID_DEFAULT = 1L;
	
	private static final int PRODUCTS_VALUE_DEFAULT = 125;
	
	private static final int TOTAL_RECEIVED_DEFAULT = 135;
	
	private PaymentRequest request;
	
	
	@BeforeEach
	public void setup() {
		request = new PaymentRequest(PRODUCTS_VALUE_DEFAULT, TOTAL_RECEIVED_DEFAULT);
	}
	
	@Test
	void testFromRequestToModel() throws Exception {
		var payment = Payment.fromRequestToModel(PLAYER_ID_DEFAULT, 12, request);
		assertEquals(PRODUCTS_VALUE_DEFAULT, payment.getProductsValue());
	}

}
