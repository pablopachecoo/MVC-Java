package com.gft.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gft.mvc.model.Titulo;
import com.gft.mvc.repository.Titulos;

@Service
public class CadastroTituloService {

	@Autowired
	private Titulos titulos;
	// private static final String CADASTRO_VIEW = "Cadastro";

	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);

		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new IllegalArgumentException("Formato invalido");
		}

	};
}
