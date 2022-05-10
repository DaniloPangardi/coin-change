package br.com.desafio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.dto.request.PaymentRequest;
import br.com.desafio.dto.response.PaymentResponse;
import br.com.desafio.service.PaymentService;

@RestController
@RequestMapping("payers")
public class PaymentController {
	
	private PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@PostMapping("{payerId}/payments/")
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentResponse payment(@PathVariable Long payerId, @Valid @RequestBody PaymentRequest request) {
		return paymentService.payment(payerId, request);
	}
	
	@GetMapping("{payerId}/payments/")
	@ResponseStatus(HttpStatus.OK)
	public List<PaymentResponse> findPayments(@PathVariable Long payerId) {
		return paymentService.findByPayerId(payerId);
	}
	

}
