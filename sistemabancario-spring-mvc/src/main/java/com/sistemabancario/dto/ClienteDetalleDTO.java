package com.sistemabancario.dto;
import java.util.List;

import com.sistemabancario.model.Banco;
import com.sistemabancario.model.Cliente;
import com.sistemabancario.model.Cuenta;

public class ClienteDetalleDTO {
	
	private Cliente cliente;
	private Banco banco;
	private List<Cuenta> cuentas;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Banco getBanco() {
		return banco;
	}
	
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	public List<Cuenta> getCuentas() {
		return cuentas;
	}
	
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
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
	
}
