package br.com.desafio.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.desafio.dto.request.PaymentRequest;


@SpringBootTest
public class PaymentServiceIntegrationTest {
	
	private static final Long PLAYER_ID_DEFAULT = 1L;
	
	private static final int PRODUCTS_VALUE_DEFAULT = 125;
	
	private static final int TOTAL_RECEIVED_DEFAULT = 135;

	@Autowired
	private PaymentService paymentService;
	
	private PaymentRequest request;
	
	
	@BeforeEach
	public void setup() {
		request = new PaymentRequest(PRODUCTS_VALUE_DEFAULT, TOTAL_RECEIVED_DEFAULT);
	}
	
	@Test
	void testPaymentWhenCorrectRequest() throws Exception {
		var transactionResponse = paymentService.payment(PLAYER_ID_DEFAULT, request);
		assertThat(transactionResponse.getPaymentId()).isNotNull();
	}
	
}
