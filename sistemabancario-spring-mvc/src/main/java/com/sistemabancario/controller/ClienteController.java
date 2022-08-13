package com.sistemabancario.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sistemabancario.business.impl.BancoServiceImpl;
import com.sistemabancario.business.impl.ClienteServiceImpl;
import com.sistemabancario.business.impl.CuentaServiceImpl;
import com.sistemabancario.dto.ClienteDetalleDTO;
import com.sistemabancario.exception.BusinessException;
import com.sistemabancario.model.Banco;
import com.sistemabancario.model.Cliente;
import com.sistemabancario.model.Cuenta;
import com.sistemabancario.util.BancoHilos;
import com.sistemabancario.util.ClienteHilos;
import com.sistemabancario.util.CuentaHilos;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteServiceImpl clienteService;
	
	@Autowired
	BancoServiceImpl bancoService;
	
	@Autowired
	CuentaServiceImpl cuentaService;
	
	@Autowired
	ClienteHilos clienteHilos;
	
	@Autowired
	BancoHilos bancoHilos;
	
	@Autowired
	CuentaHilos cuentaHilos;
	
	@RequestMapping(value="/consultar",method=RequestMethod.GET)
	public String consultar
	(@ModelAttribute("clienteCrear") Cliente clienteCrear,
			@ModelAttribute("clienteBorrar") Cliente clienteBorrar,
			@ModelAttribute("clienteActualizar") Cliente clienteActualizar,
			@ModelAttribute("clienteFiltrar") Cliente clienteFiltrar,
			@ModelAttribute("generalCrear") Cliente objetoCompleto,
			ModelMap map) {
		List<Cliente> clientes = clienteService.queryAllClients();
		map.addAttribute("listaClientes", clientes);
		return "clientesNuevo";
	}
	
	@RequestMapping(value="/crear", method=RequestMethod.POST)
	public ModelAndView crear
	(@ModelAttribute("clienteCrear") Cliente clienteCrear) {
		clienteService.registrarCliente(clienteCrear);
		return new ModelAndView("redirect:/cliente/consultar");
	}
	
	@RequestMapping(value="/borrar", method=RequestMethod.POST)
	public ModelAndView borrar(@ModelAttribute("clienteBorrar") Cliente clienteBorrar) {
		try {
			clienteService.borrarCliente(clienteBorrar);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/cliente/consultar");
	}
	
	@RequestMapping(value="/actualizar", method=RequestMethod.POST)
	public ModelAndView actualizar(@ModelAttribute("clienteActualizar") Cliente clienteActualizar) {
		try {
			clienteService.actualizarCliente(clienteActualizar);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/cliente/consultar");
	}

	@RequestMapping(value="/infoGeneral", method=RequestMethod.GET)
	public ModelAndView infoGeneral(@ModelAttribute("clienteFiltrar") Cliente clienteFiltrar) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listaClientes", clienteService.queryAllClients());
		modelAndView.addObject("listaBancos", bancoService.queryAllBanks());
		modelAndView.addObject("listaCuentas", cuentaService.queryAllAcounts());
		modelAndView.setViewName("detalles");
		return modelAndView;
	}
	
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public ModelAndView filter(@ModelAttribute("clienteFiltrar") Cliente clienteFiltrar) {
		ModelAndView modelAndView = new ModelAndView();
		ClienteDetalleDTO dto = new ClienteDetalleDTO(); 
		List<Integer> keysCuenta = new ArrayList<Integer>();
		List<Integer> keysBanco = new ArrayList<Integer>();
		List<Banco> bancos = new ArrayList<Banco>();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();

		if(clienteFiltrar.getNombre()!=null && !clienteFiltrar.getNombre().isBlank()) {
			String nombre = clienteFiltrar.getNombre();
			dto.setNombre(nombre);
		}
		if(clienteFiltrar.getaPaterno()!=null && !clienteFiltrar.getaPaterno().isBlank()) {
			String aPaterno = clienteFiltrar.getaPaterno();
			dto.setaPaterno(aPaterno);
		}
		if(clienteFiltrar.getaMaterno()!=null && !clienteFiltrar.getaMaterno().isBlank()) {
			String aMaterno = clienteFiltrar.getaMaterno();
			dto.setaMaterno(aMaterno);
		}
		
		clienteHilos.setClienteDetalleDTO(dto);
		Thread threadClientes = new Thread(clienteHilos);
		threadClientes.start();
		try {
			threadClientes.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		List<Cliente>clientes = clienteHilos.getClientes();
		
		if(!clientes.isEmpty()) {
			for(Cliente objectCliente: clientes) {
					keysCuenta.add(objectCliente.getIdCliente());
					keysBanco.add(objectCliente.getIdBanco());
			}
			bancoHilos.setKeys(keysBanco);
			cuentaHilos.setKeys(keysBanco);
			Thread threadBancos = new Thread(bancoHilos);
			Thread threadCuentas = new Thread(cuentaHilos);
			threadBancos.start();
			threadCuentas.start();
			try {
				threadBancos.join();
				threadCuentas.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bancos = bancoHilos.getBancos();
			cuentas = cuentaHilos.getCuentas();
		}
		
		modelAndView.addObject("listaCuentas", cuentas);
		modelAndView.addObject("listaClientes", clientes);
		modelAndView.addObject("listaBancos", bancos);
		modelAndView.setViewName("detalles");
		
		return modelAndView;
	}
	
	@RequestMapping(value="form", method=RequestMethod.GET)
	public String pintarPagina(@ModelAttribute("generalCrear") ClienteDetalleDTO dto) {
		return "general";
	}
	
	@RequestMapping(value="crearGeneral", method=RequestMethod.POST)
	public ModelAndView crearGeneral(@ModelAttribute("generalCrear") ClienteDetalleDTO dto) {
		ModelAndView modelAndView = new ModelAndView();
		ClienteDetalleDTO detalleDto = new ClienteDetalleDTO();
		//detalleDto.getBanco().getNombre();
		//detalleDto.banco.nombre;
		
		if(dto.getBanco().getNombre()!=null && 
				!dto.getBanco().getNombre().isBlank() && 
				dto.getBanco().getDireccion()!=null && 
				!dto.getBanco().getDireccion().isBlank()) { 
			dto.getBanco().setIdBanco(bancoService.ultimoBanco().getIdBanco()+1);
			bancoService.registrarBanco(dto.getBanco());
		}
		if(dto.getCliente().getNombre()!=null && 
				!dto.getCliente().getNombre().isBlank() &&
				dto.getCliente().getaPaterno()!=null && 
				!dto.getCliente().getaPaterno().isBlank() && 
				dto.getCliente().getaMaterno()!=null && !dto.getCliente().getaMaterno().isBlank()){
			//asigno el último id más uno al nuevo cliente
			dto.getCliente().setIdCliente(clienteService.ultimoCliente().getIdCliente()+1);
			dto.getCliente().setIdBanco(dto.getBanco().getIdBanco());
			clienteService.registrarCliente(dto.getCliente());
		}
		for(Cuenta cuenta:dto.getCuentas()) {
			if(cuenta.getNumCuenta()!=null && cuenta.getMonto()!=null) {
				cuenta.setIdCliente(dto.getCliente().getIdCliente());	
				cuenta.setIdCuenta(cuentaService.ultimaCuenta().getIdCuenta()+1);;
				cuentaService.registrarCuenta(cuenta);
			}
		}
		modelAndView.addObject("dto", dto);
		modelAndView.setViewName("generalListado");
		return modelAndView;	
	}
}
