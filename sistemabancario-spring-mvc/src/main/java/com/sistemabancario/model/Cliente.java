package com.sistemabancario.model;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


public class Cliente {
	private Integer idCliente;
	private Integer idBanco;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private List<Cuenta> cuentas= new ArrayList<Cuenta>();
	
	public Cliente() {}
	
	public Cliente(Integer idCliente, Integer idBanco, String nombre, String aPaterno, String aMaterno, List<Cuenta> cuentas) {
		this.idCliente=idCliente;
		this.nombre=nombre;
		this.aPaterno=aPaterno;
		this.aMaterno=aMaterno;
		this.cuentas=cuentas;
		this.idBanco=idBanco;
	}
	
	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public void agregarCuenta(Cuenta c) {
		cuentas.add(c);
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getaPaterno() {
		return aPaterno;
	}

	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}

	public String getaMaterno() {
		return aMaterno;
	}

	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

}
