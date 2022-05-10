package br.com.desafio.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.desafio.model.Payment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PaymentResponse {
	
	private Long paymentId;
	
	private Integer productsValue;
	
    private Integer totalReceived;
    
    private Integer discountAmount;
    
    @JsonInclude(Include.NON_NULL)
    private Exchange exchange;

	public static PaymentResponse fromModelToResponse(Payment payment) {
		var paymentResponse = new PaymentResponse();
		BeanUtils.copyProperties(payment, paymentResponse);
		return paymentResponse;
	}
	
	public int calculateExchangeValue() {
		return totalReceived - (productsValue - discountAmount);
	}
	
}