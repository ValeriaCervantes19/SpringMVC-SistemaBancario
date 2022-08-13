package com.sistemabancario.model;

import org.springframework.stereotype.Component;

public class Cuenta {
	private Integer idCuenta;
	private Integer numCuenta;
	private Integer idCliente;
	private Float monto;
	
	public Cuenta() {}
	public Cuenta(Integer idCuenta, Integer numCuenta, Integer idCliente, Float monto) {
		this.idCuenta=idCuenta;
		this.numCuenta=numCuenta;
		this.monto=monto;
		this.idCliente=idCliente;
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdCuenta() {
		return idCuenta;
	}
	
	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}
	
	public void setNumCuenta(Integer numCuenta) {
		this.numCuenta=numCuenta;
	}
	
	public Integer getNumCuenta() {
		return numCuenta;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}
	
}
