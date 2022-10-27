package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaceService.IformularioService;
import com.example.demo.interfaces.IFormulario;
import com.example.demo.model.Formulario;

@Service
public class FormularioService implements IformularioService{
	
	@Autowired
	private IFormulario iFormulario;
	@Override
	public List<Formulario> listar() {
		return (List<Formulario>)iFormulario.findAll();
	}

	@Override
	public Optional<Formulario> listarId(int id) {
		return iFormulario.findById(id);
	}

	@Override
	public int save(Formulario f) {
		int res = 1;
		Formulario formulario = iFormulario.save(f);
		if(formulario.equals(null)) {
			res = 0;
		}
		return res;
	}

	@Override
	public void delete(int id) {
		iFormulario.deleteById(id);
		
	}

}
