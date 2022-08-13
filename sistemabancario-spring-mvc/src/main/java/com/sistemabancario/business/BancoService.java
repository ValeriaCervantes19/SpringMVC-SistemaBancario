package com.sistemabancario.business;

import java.util.List;

import com.sistemabancario.exception.BusinessException;
import com.sistemabancario.model.Banco;


public interface BancoService {
	public List<Banco> queryAllBanks();
	public void registrarBanco(Banco banco);
	public void borrarBanco(Banco banco) throws BusinessException;
	public void actualizarBanco(Banco banco) throws BusinessException;
	public Banco consultarBanco(Integer id) throws BusinessException;
	public List<Banco> filtrarBancos(List<Integer> keys);
	public Banco ultimoBanco();
}
