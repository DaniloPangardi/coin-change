package br.com.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.desafio.dto.request.PaymentRequest;
import br.com.desafio.exception.ResourceNotFoundException;
import br.com.desafio.model.Payment;
import br.com.desafio.repository.PaymentRepository;

public class PaymentServiceTest {

	private static final Long PLAYER_ID_DEFAULT = 1L;
	
	private static final int PRODUCTS_VALUE_DEFAULT = 125;
	
	private static final int TOTAL_RECEIVED_DEFAULT = 135;

	@InjectMocks
	private PaymentService paymentService;

	@Mock
	private PaymentRepository paymentRepository;

	@Captor
	private ArgumentCaptor<Payment> paymentCaptor;
	
	private PaymentRequest request;

	private Payment payment;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		request = new PaymentRequest(PRODUCTS_VALUE_DEFAULT, TOTAL_RECEIVED_DEFAULT);
		payment = buildPayment();
	}

	@Test
	void testPaymentWhenNotHasCompleted() throws Exception {

		when(paymentRepository.save(Mockito.any())).thenReturn(payment);

        paymentService.payment(PLAYER_ID_DEFAULT, request);
		
		verify(paymentRepository, times(1)).save(paymentCaptor.capture());

		assertEquals(PLAYER_ID_DEFAULT, paymentCaptor.getValue().getPayerId());
		
	}
	
	@Test
	void testPaymentWhenHasCompleted() throws Exception {

		when(paymentRepository.totalPurchaseByPayerId(Mockito.anyLong())).thenReturn(2);
		
		when(paymentRepository.save(Mockito.any())).thenReturn(payment);

        paymentService.payment(PLAYER_ID_DEFAULT, request);
		
		verify(paymentRepository, times(1)).save(paymentCaptor.capture());

		assertEquals(PLAYER_ID_DEFAULT, paymentCaptor.getValue().getPayerId());
		
	}

	@Test
	void testGetByUserIdWhenResourceFound() throws Exception {
		when(paymentRepository.findByPayerId(Mockito.any())).thenReturn(Arrays.asList(payment));
		assertNotNull(paymentService.findByPayerId(PLAYER_ID_DEFAULT));
	}

	@Test
	void testByPayerIdWhenResourceNotFound() throws Exception {
		ResourceNotFoundException assertThrows = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			paymentService.findByPayerId(PLAYER_ID_DEFAULT);
		});
		assertEquals(ResourceNotFoundException.class, assertThrows.getClass());
	}
	
	private Payment buildPayment() {
		var payment = new Payment();
		payment.setPayerId(PLAYER_ID_DEFAULT);
		payment.setTotalReceived(TOTAL_RECEIVED_DEFAULT);
		payment.setProductsValue(PRODUCTS_VALUE_DEFAULT);
		payment.setDiscountAmount(12);
		return payment;
	}

}
