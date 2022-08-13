package com.sistemabancario.business;

import java.util.List;

import com.sistemabancario.dto.ClienteDetalleDTO;
import com.sistemabancario.dto.CuentaDTO;
import com.sistemabancario.exception.BusinessException;
import com.sistemabancario.model.Cliente;
import com.sistemabancario.model.Cuenta;

public interface ClienteService {
	public List<Cliente> queryAllClients();
	public void registrarCliente(Cliente cliente);
	public void borrarCliente(Cliente cliente) throws BusinessException;
	public void actualizarCliente(Cliente cliente) throws BusinessException;
	public ClienteDetalleDTO getDetalleCliente(Integer id) throws BusinessException;
	public Cliente consultarCliente(Integer id) throws BusinessException;
	public List<Cliente> filtrarClientes(ClienteDetalleDTO dtoCliente);
	public Cliente ultimoCliente();
}
