package br.com.desafio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

import br.com.desafio.dto.request.PaymentRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode(of = "paymentId")
@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;
	
	private Long payerId;
	
	private Integer productsValue;
	
	private Integer totalReceived;
	
	private Integer discountAmount;
	
	
	public static Payment fromRequestToModel(Long payerId, int discountAmount, PaymentRequest paymentRequest) {
		Payment payment = new Payment();
		payment.setPayerId(payerId);
		payment.setDiscountAmount(discountAmount);
		BeanUtils.copyProperties(paymentRequest, payment);
		return payment;
	}

}
