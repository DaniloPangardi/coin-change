package br.com.desafio.dto.request;

import java.math.BigDecimal;
import java.text.MessageFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.desafio.exception.InsufficientTotalAmountException;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
	
	private static final String MESSAGE_ERROR_TOTAL_RECEIVED_PATTERN = "Insufficient total amount received: productsValue: {0} -> totalReceived: {1}.";

	@ApiModelProperty(value = "Valor dos produtos", name = "productsValue", dataType = "Integer", example = "125")
	@NotNull
	@Min(value = 1)
	private Integer productsValue;

	@ApiModelProperty(value = "Valor total recebido", name = "totalReceived", dataType = "Integer", example = "135")
	@NotNull
	@Min(value = 1)
	private Integer totalReceived;
	
	public void validateValues()	{
		if(totalReceived < productsValue) {
			var messageError = MessageFormat.format(MESSAGE_ERROR_TOTAL_RECEIVED_PATTERN, productsValue, totalReceived);
			throw new InsufficientTotalAmountException(messageError);
		}
	}
	
	public boolean hasCompleted(int totalPurchases) {
		var rest = totalPurchases(totalPurchases) % 3;
		return rest == 0;
	}

	private int totalPurchases(int totalPurchases) {
		return totalPurchases + 1;
	}
	
	public int calculateDiscount(int productsValue) {
		return new BigDecimal(productsValue)
				.multiply(BigDecimal.TEN.divide(new BigDecimal(100)))
				.intValue();
	}


}
