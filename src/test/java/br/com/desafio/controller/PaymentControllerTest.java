package br.com.desafio.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.desafio.dto.request.PaymentRequest;
import br.com.desafio.service.PaymentService;

public class PaymentControllerTest {
	
	private static final Long PLAYER_ID_DEFAULT = 1L;
	
	@InjectMocks
	private PaymentController paymentController;
	
	@Mock
	private PaymentService paymentService;
	
	private PaymentRequest request;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		request = new PaymentRequest();
	}
	
	@Test
	void testSaveCallWhenRequestOk() throws Exception {
		paymentController.payment(PLAYER_ID_DEFAULT, request);
		verify(paymentService, times(1)).payment(Mockito.anyLong(), Mockito.any());
	}
	
	
	@Test
	void testGetByPayerIdCallWhenRequestOk() throws Exception {
		paymentController.findPayments(PLAYER_ID_DEFAULT);
		verify(paymentService, times(1)).findByPayerId(Mockito.anyLong());
	}
	
	

}
