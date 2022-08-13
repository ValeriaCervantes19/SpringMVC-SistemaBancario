package com.sistemabancario.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistemabancario.business.BancoService;
import com.sistemabancario.dto.BancoDTO;
import com.sistemabancario.exception.BusinessException;
import com.sistemabancario.model.Banco;
import com.sistemabancario.model.Cliente;
import com.sistemabancario.persistence.GenericDao;
import com.sistemabancario.persistence.impl.BancoDao;

@Component
public class BancoServiceImpl implements BancoService{

	@Autowired
	private BancoDao bancoDao;// = new BancoDao();
	@Autowired
	@Qualifier("bancoDao")
	private GenericDao dao1;
	@Autowired
	@Qualifier("clienteDao")
	private GenericDao dao2;
	
	/* Otras formas de inyectar
	@Autowired
	public void setBancoDao(BancoDao bancoDao) {
		this.bancoDao = bancoDao;
	}

	@Autowired
	public BancoServiceImpl(BancoDao bancoDao) {
		this.bancoDao =bancoDao;
	};
	*/
	
	@Override
	public List<Banco> queryAllBanks() {
		//BancoDao bancoDao = new BancoDao();
		return bancoDao.findAll();
	}
	
	@Override
	public void registrarBanco(Banco banco) {
		//BancoDao bancoDao = new BancoDao();
		bancoDao.create(banco);
	}
	
	@Override
	public void borrarBanco(Banco banco) throws BusinessException {
		if(bancoDao.findById(banco.getIdBanco())!=null) {
			bancoDao.delete(banco);
		}
		else {
			throw new BusinessException("El banco no existe");
		}
	}
	
	@Override
	public void actualizarBanco(Banco banco) throws BusinessException{
		if(bancoDao.findById(banco.getIdBanco())!=null) {
			bancoDao.update(banco);
		}
		else {
			throw new BusinessException("El banco no existe");
		}
	}

	@Override
	public Banco consultarBanco(Integer id) throws BusinessException {
		Banco banco=null;
		if(bancoDao.findById(id)!=null) {
			banco=(Banco)bancoDao.findById(id);
		}
		return banco;
	}
	
	@Override
	public List<Banco> filtrarBancos(List<Integer> keys) {
		List<Banco> bancos = null;
		if(keys!=null) {;
			bancos = (List<Banco>) bancoDao.findAllById(keys);
		}
		return bancos;
	}

	@Override
	public Banco ultimoBanco() {
		Object object=bancoDao.lastElement();
		Banco banco=null;
		if(object!=null) {
			banco = (Banco)object;
		}
		return banco;
	}
	
}
