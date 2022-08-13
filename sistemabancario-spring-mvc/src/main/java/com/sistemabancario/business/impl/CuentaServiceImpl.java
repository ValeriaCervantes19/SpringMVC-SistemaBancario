package com.sistemabancario.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemabancario.business.CuentaService;
import com.sistemabancario.dto.CuentaDTO;
import com.sistemabancario.exception.BusinessException;
import com.sistemabancario.model.Banco;
import com.sistemabancario.model.Cuenta;
import com.sistemabancario.persistence.impl.CuentaDao;

@Component
public class CuentaServiceImpl implements CuentaService{

	@Autowired
	private CuentaDao cuentaDao;// = new CuentaDao();
	
	@Override
	public List<Cuenta> queryAllAcounts() {		 
		return cuentaDao.findAll();
	}

	@Override
	public void registrarCuenta(Cuenta cuenta) {
		cuentaDao.create(cuenta);
	}

	@Override
	public void borrarCuenta(Cuenta cuenta) throws BusinessException {
		if(cuentaDao.findById(cuenta.getIdCuenta())!=null) {
			cuentaDao.delete(cuenta);
		}
		else {
			throw new BusinessException("La cuenta no existe");
		}
	}

	@Override
	public void actualizarCuenta(Cuenta cuenta) throws BusinessException{
		if(cuentaDao.findById(cuenta.getIdCuenta())!=null) {
			cuentaDao.update(cuenta);
		}
		else {
			throw new BusinessException("La cuenta no existe");
		}
	}

	@Override
	public List<Cuenta> filtrarCuentas(CuentaDTO dtoCuenta) {
		Map<String, Object> mapaFilter = new HashMap<>();
		
		mapaFilter.put("idCliente", dtoCuenta.getIdCliente());
		if(dtoCuenta.getIdCuenta()!=null) {
			mapaFilter.put("idCuenta", dtoCuenta.getIdCuenta());
		}
		if(dtoCuenta.getNumCuenta()!=null) {
			mapaFilter.put("numCuenta", dtoCuenta.getNumCuenta());
		}
		if(dtoCuenta.getMonto()!=null) {
			mapaFilter.put("monto", dtoCuenta.getMonto());
		}
		List<Cuenta> cuentas = (List<Cuenta>) cuentaDao.findByExample(mapaFilter);
		
		return cuentas;
	}

	@Override
	public List<Cuenta> filtrarCuentas(List<Integer> keysCuenta) {
		List<Cuenta> cuentas = null;
		if(keysCuenta!=null) {
			cuentaDao.asignarLlaves(keysCuenta);
			Thread threadCuenta = new Thread(cuentaDao);
			threadCuenta.start();
			try {
				threadCuenta.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cuentas =cuentaDao.getListaCuentas();
		}
		return cuentas;
	}

	@Override
	public Cuenta ultimaCuenta() {
		Object object = cuentaDao.lastElement();
		Cuenta cuenta=null;
		if(object!=null) {
			cuenta = (Cuenta)object;
		}
		return cuenta;
	}
	
	
}
