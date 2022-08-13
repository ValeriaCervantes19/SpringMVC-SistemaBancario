package com.sistemabancario.business.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemabancario.business.ClienteService;
import com.sistemabancario.dto.ClienteDetalleDTO;
import com.sistemabancario.exception.BusinessException;
import com.sistemabancario.model.Banco;
import com.sistemabancario.model.Cliente;
import com.sistemabancario.model.Cuenta;
import com.sistemabancario.persistence.impl.BancoDao;
import com.sistemabancario.persistence.impl.ClienteDao;
import com.sistemabancario.persistence.impl.CuentaDao;

@Component
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteDao clienteDao;// = new ClienteDao();
	
	@Override
	public List<Cliente> queryAllClients() {
		return clienteDao.findAll();
	}
	
	@Override
	public void registrarCliente(Cliente cliente) {
		clienteDao.create(cliente);
	}

	@Override
	public void borrarCliente(Cliente cliente) throws BusinessException {	
		if(clienteDao.findById(cliente.getIdCliente())!=null) {
			clienteDao.delete(cliente);
		}
		else {
			throw new BusinessException("El cliente no existe");
		}
	}

	@Override
	public void actualizarCliente(Cliente cliente) throws BusinessException {
		if(clienteDao.findById(cliente.getIdCliente())!=null) {
			clienteDao.update(cliente);
		}
		else {
			throw new BusinessException("El cliente no existe");
		}
	}

	@Override
	public ClienteDetalleDTO getDetalleCliente(Integer id) throws BusinessException {
		//@Autowired
		BancoDao bancoDao = new BancoDao();
		CuentaDao cuentaDao = new CuentaDao();
		ClienteDetalleDTO dto = new ClienteDetalleDTO();
		Object clienteO = clienteDao.findById(id);
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		if(clienteO!=null) {
			Cliente cliente =(Cliente)clienteO;
			dto.setCliente(cliente);
			Banco banco =(Banco)bancoDao.findById(cliente.getIdBanco());
			dto.setBanco(banco);
			Map<String, Object> mapDTO =new HashMap<String, Object>();
			mapDTO.put("idCliente", cliente.getIdCliente());
			//List<Cuenta> cuentas = (List<Cuenta>)cuentaDao.findByExample(mapDTO);
			//dto.setCuentas(cuentas);
			for(Object object:cuentaDao.findByExample(mapDTO)) {
				Cuenta cuenta = (Cuenta)object;
				cuentas.add(cuenta);
			}
			dto.setCuentas(cuentas);
		}
		else {
			throw new BusinessException("El cliente no existe");
		}
		return dto;
	}

	@Override
	public Cliente consultarCliente(Integer id) throws BusinessException {
		Cliente cliente = null;
		if(clienteDao.findById(id)!=null) {
			cliente = (Cliente)clienteDao.findById(id);
		}
		return cliente;
	}
	
	public Cliente ultimoCliente() {
		Object object = clienteDao.lastElement();
		Cliente cliente=null;
		if(object!=null) {
			cliente=(Cliente)object;
		}
		return cliente;
	}

	@Override
	public List<Cliente> filtrarClientes(ClienteDetalleDTO dtoCliente) {
		// se agrega el Autowired o no?
		Map<String, Object> mapaFilter = new HashMap<>();
		
		if(dtoCliente.getNombre()!=null) {
			mapaFilter.put("nombre", dtoCliente.getNombre());
		}
		if(dtoCliente.getaPaterno()!=null) {
			mapaFilter.put("aPaterno", dtoCliente.getaPaterno());
		}
		if(dtoCliente.getaMaterno()!=null) {
			mapaFilter.put("aMaterno", dtoCliente.getaMaterno());
		}
		List<Cliente> clientes = (List<Cliente>) clienteDao.findByExample(mapaFilter);
		
		return clientes;
	}
	
}
