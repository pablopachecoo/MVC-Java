package com.gft.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.gft.mvc.model.StatusTitulo;
import com.gft.mvc.model.Titulo;
import com.gft.mvc.repository.Titulos;

import javassist.expr.NewArray;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	@Autowired
	private Titulos titulos;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView m = new ModelAndView("CadastroTitulo");
		m.addObject("todosStatus", StatusTitulo.values());
		m.addObject(new Titulo());
		return m;
	}

	@RequestMapping("/ver")
	public ModelAndView ver() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	
	@RequestMapping
	public String pesquisa() {
		return "PesquisaTitulos";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors error, RedirectAttributes attributes) {
		if (error.hasErrors()) {
			return "CadastroTitulo";
		}
		titulos.save(titulo);
		attributes.addFlashAttribute("mensagem", "Titulo salvo com sucesso");
		return "redirect:/titulos/novo";
		// SALVAR NO BANCO
		
		///mv.addObject("mensagem", "Título salvo com sucesso!");
		//return mv;
	}
}
