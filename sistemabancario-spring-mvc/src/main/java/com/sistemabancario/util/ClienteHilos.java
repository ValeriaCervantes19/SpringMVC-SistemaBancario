package com.sistemabancario.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemabancario.business.ClienteService;
import com.sistemabancario.business.impl.ClienteServiceImpl;
import com.sistemabancario.dto.ClienteDetalleDTO;
import com.sistemabancario.model.Cliente;

@Component
public class ClienteHilos implements Runnable{
	
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private ClienteDetalleDTO clienteDetalleDTO = new ClienteDetalleDTO();
	@Autowired
	private ClienteServiceImpl clienteService;
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	} 

	public ClienteDetalleDTO getClienteDetalleDTO() {
		return clienteDetalleDTO;
	}
	
	public void setClienteDetalleDTO(ClienteDetalleDTO clienteDetalleDTO) {
		this.clienteDetalleDTO = clienteDetalleDTO;
	}

	@Override
	public void run() {
		clientes =clienteService.filtrarClientes(clienteDetalleDTO);
	}
	
}
