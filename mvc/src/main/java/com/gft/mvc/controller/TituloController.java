package com.gft.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TituloController {
	
	@RequestMapping("/titulos/criar")
	public String Novo() {
		return "CadastroTitulo";
	}

}
