package com.sistemabancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sistemabancario.business.impl.BancoServiceImpl;
import com.sistemabancario.model.Banco;
import com.sistemabancario.model.UsuarioEjemplo;

@Controller
//definir con que url llegar al controlador
@RequestMapping("/holaMundo")
public class HolaMundoMVCController {

	@Autowired
	BancoServiceImpl bancoSer;
	
	//para acceder al método se hace mediante un get
	@RequestMapping(method=RequestMethod.GET)
	public String holaMundo
	(@ModelAttribute("usuarioEjemplo") UsuarioEjemplo usuario,
			ModelMap map) {
		List<Banco>bancos = bancoSer.queryAllBanks();
		map.addAttribute("mensaje", "hola mundo");
		map.addAttribute("listaBancos", bancos);
		return "holamundomvc";
	}
	
	@RequestMapping(value="/registrarUsuario", method=RequestMethod.POST)
	public String registrarUsuario
		(@ModelAttribute("usuarioEjemplo") UsuarioEjemplo usuario,
				ModelMap map) {
		System.out.println(usuario.getNombre());
		map.addAttribute("usuario", usuario);
		return "holamundomvc";
	}
}
