package com.sistemabancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sistemabancario.business.impl.BancoServiceImpl;
import com.sistemabancario.exception.BusinessException;
import com.sistemabancario.model.Banco;

@Controller
@RequestMapping("/banco")
public class BancosController {

	@Autowired
	BancoServiceImpl bancoService;
	
	@RequestMapping(value="/consultar",method=RequestMethod.GET)
	public ModelAndView consultar(@ModelAttribute("bancoCrear") Banco bancoCrear,
			@ModelAttribute("bancoBorrar") Banco bancoBorrar,
			@ModelAttribute("bancoActualizar") Banco bancoActualizar,
			ModelMap map) {
		List<Banco> bancos = bancoService.queryAllBanks();
		map.addAttribute("listaBancos", bancos);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listaBancos", bancos);
		modelAndView.setViewName("bancosNuevo");
		return modelAndView;
		//return "bancosNuevo";
	}
	
	@RequestMapping(value="/crear", method=RequestMethod.POST)
	public ModelAndView crear(@ModelAttribute("bancoCrear") Banco bancoCrear,
			ModelMap map) {
		bancoService.registrarBanco(bancoCrear);
		map.addAttribute("bancoCrear", bancoCrear);
		return new ModelAndView("redirect:/banco/consultar"); 
	}
	
	@RequestMapping(value="/borrar", method=RequestMethod.POST)
	public ModelAndView borrar(@ModelAttribute("bancoBorrar") Banco bancoBorrar,
			ModelMap map) {
		try {
			bancoService.borrarBanco(bancoBorrar);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/banco/consultar");
	}
	
	@RequestMapping(value="/actualizar", method=RequestMethod.POST)
	public ModelAndView actualizar(@ModelAttribute("bancoActualizar") Banco bancoActualizar, ModelMap map) {
		try {
			bancoService.actualizarBanco(bancoActualizar);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/banco/consultar");
	}
	
	@RequestMapping(value="/infoGeneral", method=RequestMethod.GET)
	public ModelAndView infoGeneral() {
		ModelAndView modelAndView = new ModelAndView("redirect:/cuenta/infoGeneral");
		modelAndView.addObject("listaBancos", bancoService.queryAllBanks());
		return modelAndView;
	}
}
