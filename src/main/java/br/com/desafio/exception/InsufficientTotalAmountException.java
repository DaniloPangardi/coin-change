package br.com.desafio.exception;

public class InsufficientTotalAmountException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InsufficientTotalAmountException(String message) {
		super(message);
	}

}
