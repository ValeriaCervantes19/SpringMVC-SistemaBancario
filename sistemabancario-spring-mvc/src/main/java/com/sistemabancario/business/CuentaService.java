package com.sistemabancario.business;
import com.sistemabancario.dto.CuentaDTO;
import com.sistemabancario.exception.BusinessException;
import com.sistemabancario.model.Banco;
import com.sistemabancario.model.Cuenta;
import java.util.List;

public interface CuentaService {
	public List<Cuenta> queryAllAcounts();
	public void registrarCuenta(Cuenta cuenta);
	public void borrarCuenta(Cuenta cuenta) throws BusinessException;
	public void actualizarCuenta(Cuenta cuenta) throws BusinessException;
	public List<Cuenta> filtrarCuentas(CuentaDTO dtoCuenta);
	public List<Cuenta> filtrarCuentas(List<Integer> keysCuenta); 
	public Cuenta ultimaCuenta();
}
