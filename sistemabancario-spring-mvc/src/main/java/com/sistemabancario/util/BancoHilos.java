package com.sistemabancario.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemabancario.business.BancoService;
import com.sistemabancario.business.impl.BancoServiceImpl;
import com.sistemabancario.model.Banco;

@Component
public class BancoHilos implements Runnable{
	
	private List<Integer> keys= new ArrayList<Integer>();
	private List<Banco> bancos = new ArrayList<Banco>();
	
	@Autowired
	BancoService bancoService;

	public List<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}	
	
	public List<Integer> getKeys() {
		return keys;
	}

	public void setKeys(List<Integer> keys) {
		this.keys = keys;
	}

	@Override
	public void run() {
		bancos=bancoService.filtrarBancos(keys);
	}
	
}
