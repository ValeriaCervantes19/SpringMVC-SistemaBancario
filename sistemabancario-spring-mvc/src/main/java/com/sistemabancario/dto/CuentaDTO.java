package com.sistemabancario.dto;

import java.util.ArrayList;
import java.util.List;

public class CuentaDTO {
	private Integer idCuenta;
	private Integer numCuenta;
	private Float monto;
	private Integer idCliente;
	private List<Integer> keysCuenta = new ArrayList<Integer>();
	
	public List<Integer> getKeysCuenta() {
		return keysCuenta;
	}

	public void setKeysCuenta(List<Integer> keysCuenta) {
		this.keysCuenta = keysCuenta;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}
	
	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}
	
	public Integer getNumCuenta() {
		return numCuenta;
	}
	
	public void setNumCuenta(Integer numCuenta) {
		this.numCuenta = numCuenta;
	}
	
	public Float getMonto() {
		return monto;
	}
	
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
}
