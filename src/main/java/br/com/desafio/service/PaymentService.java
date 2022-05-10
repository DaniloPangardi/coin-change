package br.com.desafio.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.dto.request.PaymentRequest;
import br.com.desafio.dto.response.Exchange;
import br.com.desafio.dto.response.PaymentResponse;
import br.com.desafio.exception.ResourceNotFoundException;
import br.com.desafio.model.Payment;
import br.com.desafio.repository.PaymentRepository;

@Service
public class PaymentService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PaymentService.class);
	
	private static final int SEVENTEEN = 17;

	private PaymentRepository paymentRepository;

	@Autowired
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public PaymentResponse payment(Long payerId, PaymentRequest request) {

		LOG.info("Request object received: {}. PayerId: {}. Validating values...", request, payerId);
		
		request.validateValues();
		
		int totalPurchases = paymentRepository.totalPurchaseByPayerId(payerId);
		
		var discountAmount = 0;
		
		if (request.hasCompleted(totalPurchases)) {
			
			LOG.info("Client: {} has completed total purchase. Will compete for the discount...", payerId);

			if(hasWon()) {
				discountAmount =  request.calculateDiscount(request.getProductsValue());
				LOG.info("Discount amount: {}. Client: {}.", discountAmount, payerId);
			}
		
		}

		var payment = Payment.fromRequestToModel(payerId, discountAmount, request);

		LOG.info("Saving Payment. Object model: {}", payment);
		payment = paymentRepository.save(payment);

		var response = PaymentResponse.fromModelToResponse(payment);
		
		LOG.info("Payment saved successfully. Object{}", payment);
		var exchangeValue = response.calculateExchangeValue();
		
		response.setExchange(Exchange.buildExchange(exchangeValue));
		
		LOG.info("Response object with exchange value calculed. Object{}", payment);
		return response;
	}
	
	
	
	private boolean hasWon() {
		return new Random().nextInt(99) < SEVENTEEN;
	}

	public List<PaymentResponse> findByPayerId(Long payerId) {

		LOG.info("Getting transactions from payerId: {}", payerId);
		var payments = paymentRepository.findByPayerId(payerId);

		if (payments.isEmpty()) {
			throw new ResourceNotFoundException("Resource not found for this payerId.");
		}

		LOG.info("{} payment(s) found from payerId: {}.", payments.size(), payerId);
		return payments.stream()
				.map(payment -> PaymentResponse.fromModelToResponse(payment))
				.collect(Collectors.toList());

	}

}
