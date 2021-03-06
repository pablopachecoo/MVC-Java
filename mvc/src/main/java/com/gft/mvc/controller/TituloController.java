package com.gft.mvc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.ast.Var;
import org.hibernate.cache.spi.support.AbstractCachedDomainDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.gft.mvc.model.StatusTitulo;
import com.gft.mvc.model.Titulo;
import com.gft.mvc.repository.Titulos;
import com.gft.mvc.service.CadastroTituloService;

import javassist.expr.NewArray;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	private static final String CADASTRO_VIEW = "Cadastro";
	@Autowired
	private Titulos titulos;

	@Autowired
	private CadastroTituloService cadastroTituloService;

	@RequestMapping(value = "/novo", method = RequestMethod.GET) // @RequestMapping(value = "/login", method =
																	// RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView m = new ModelAndView("Cadastro");
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

	@RequestMapping()
	public String pesquisa() {
		return "PesquisaTitulos";
	}

	@RequestMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Titulo titulo) {

		ModelAndView mv = new ModelAndView("EditarTitulo");
		mv.addObject("todosStatus", StatusTitulo.values());
		mv.addObject(titulo);
		return mv;
	}

	//METODO PARA SALVAR NO BANCO DE DADOS
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors error, RedirectAttributes attributes) {
		if (error.hasErrors()) {
			return CADASTRO_VIEW;
		}
		try {
			cadastroTituloService.salvar(titulo);
			attributes.addFlashAttribute("mensagem", "Titulo salvo com sucesso");
			return "redirect:/titulos/novo";
		} catch (IllegalArgumentException e) {
			error.rejectValue("dataVencimento", null, e.getMessage());
			// jhj mv.addObject("todosStatus", StatusTitulo.values());
			return CADASTRO_VIEW;
		}

	}

	@RequestMapping("/deletar/{codigo}")
	public String deletarr(@PathVariable("codigo") long codigo) {

		this.titulos.deleteById(codigo);

		return "redirect:/titulos/ver";
		// SALVAR NO BANCO

		/// mv.addObject("mensagem", "Título salvo com sucesso!");
		// return mv;
	}
	
	@RequestMapping(value = "/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo) {
		return cadastroTituloService.receber(codigo);
	}
	
	
	@ModelAttribute("StatusTitulo")
	public List<StatusTitulo> StatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
}
