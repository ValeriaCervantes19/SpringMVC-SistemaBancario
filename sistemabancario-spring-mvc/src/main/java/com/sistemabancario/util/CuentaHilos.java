package com.sistemabancario.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemabancario.business.CuentaService;
import com.sistemabancario.business.impl.CuentaServiceImpl;
import com.sistemabancario.model.Cuenta;

@Component
public class CuentaHilos implements Runnable{

	private List<Integer> keys= new ArrayList<Integer>();
	private List<Cuenta> cuentas = new ArrayList<Cuenta>();
	
	@Autowired
	CuentaService cuentaService;
	
	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	
	public List<Integer> getKeys() {
		return keys;
	}

	public void setKeys(List<Integer> keys) {
		this.keys = keys;
	}

	@Override
	public void run() {
		cuentas = cuentaService.filtrarCuentas(keys);
	}

}
