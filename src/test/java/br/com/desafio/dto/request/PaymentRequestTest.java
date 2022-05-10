package br.com.desafio.dto.request;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.desafio.exception.InsufficientTotalAmountException;

public class PaymentRequestTest {
	
	private static final int PRODUCTS_VALUE_DEFAULT = 125;
	
	private static final int TOTAL_RECEIVED_DEFAULT = 135;
	
	private PaymentRequest request;
	
	@BeforeEach
	public void setup() {
		request = new PaymentRequest(PRODUCTS_VALUE_DEFAULT, TOTAL_RECEIVED_DEFAULT);
	}
	
	
	@Test
	void testValidateValuesWhenInsufficientTotalAmountException() throws Exception {
		InsufficientTotalAmountException assertThrows = Assertions.assertThrows(InsufficientTotalAmountException.class, () -> {
			request.setProductsValue(136);
			request.validateValues();
		});
		assertEquals(InsufficientTotalAmountException.class, assertThrows.getClass());
	}
	
	@Test
	void testValidateValuesWhenRequestOk() throws Exception {
		request.validateValues();
		assertEquals(PRODUCTS_VALUE_DEFAULT, request.getProductsValue());
	}
	
	@Test
	void testHasCompletedWhenHasCompleted() throws Exception {
		assertTrue(request.hasCompleted(2));
	}
	
	@Test
	void testHasCompletedWhenNotHasCompleted() throws Exception {
		assertFalse(request.hasCompleted(1));
	}
	
	@Test
	void testCalculateDiscountWhenValueIsInteger() throws Exception {
		assertEquals(10, request.calculateDiscount(100));
	}
	
	@Test
	void testCalculateDiscountWhenValueIsRounded() throws Exception {
		assertEquals(12, request.calculateDiscount(125));
	}

}
