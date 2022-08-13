package com.sistemabancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sistemabancario.business.impl.CuentaServiceImpl;
import com.sistemabancario.exception.BusinessException;
import com.sistemabancario.model.Cliente;
import com.sistemabancario.model.Cuenta;

@Controller
@RequestMapping("/cuenta")
public class CuentaController {
	
	@Autowired
	private CuentaServiceImpl cuentaService;
	
	@RequestMapping(value="/consultar",method=RequestMethod.GET)
	public String consultar
	(@ModelAttribute("cuentaCrear") Cuenta cuentaCrear,
			@ModelAttribute("cuentaBorrar") Cuenta cuentaBorrar,
			@ModelAttribute("cuentaActualizar") Cuenta cuentaActualizar,
			ModelMap map) {
		List<Cuenta>cuentas = cuentaService.queryAllAcounts();
		map.addAttribute("listaCuentas", cuentas);
		return "cuentasNuevo";
	}
	
	@RequestMapping(value="/crear", method=RequestMethod.POST)
	public ModelAndView crear(@ModelAttribute("cuentaCrear") Cuenta cuentaCrear) {
		cuentaService.registrarCuenta(cuentaCrear);
		return new ModelAndView("redirect:/cuenta/consultar");
	}
	
	@RequestMapping(value="/borrar", method=RequestMethod.POST)
	public ModelAndView borrar(@ModelAttribute("cuentaBorrar") Cuenta cuentaBorrar) {
		try {
			cuentaService.borrarCuenta(cuentaBorrar);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/cuenta/consultar");
	}
	
	@RequestMapping(value="actualizar", method=RequestMethod.POST)
	public ModelAndView actualizar(@ModelAttribute("cuentaActualizar") Cuenta cuentaActualizar) {
		try {
			cuentaService.actualizarCuenta(cuentaActualizar);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/cuenta/consultar");
	}
	
	//por qué se cambia a GET?
	@RequestMapping(value="/infoGeneral", method=RequestMethod.GET)
	public ModelAndView infoGeneral(@ModelAttribute("clienteFiltrar") Cliente clienteFiltrar) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listaCuentas", cuentaService.queryAllAcounts());
		modelAndView.setViewName("detalles");
		return modelAndView;
	}
}
