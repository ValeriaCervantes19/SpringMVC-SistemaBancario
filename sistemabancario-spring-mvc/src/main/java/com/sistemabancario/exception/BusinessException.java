package com.sistemabancario.exception;

public class BusinessException extends Exception{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	public BusinessException(String cause){
		//se manda una string al constructor padre
		super(cause);
	}
}
