package br.com.desafio.dto.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.desafio.model.Payment;

public class PaymentResponseTest {
	
	private PaymentResponse paymentResponse;
	
	private Payment payment;
	
	@BeforeEach
	public void setup() {
		payment = new Payment();
		paymentResponse = buildResponse();
	}
	
	@Test
	void testFromModelToResponse() throws Exception {
		assertNotNull(PaymentResponse.fromModelToResponse(payment));
	}
	
	@Test
	void testCalculateExchangeValue() throws Exception {
		assertEquals(22, paymentResponse.calculateExchangeValue());
	}
	
	
	private PaymentResponse buildResponse() {
		var response = new PaymentResponse();
		response.setTotalReceived(135);
		response.setProductsValue(125);
		response.setDiscountAmount(12);
		return response;
	}

}
