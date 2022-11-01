package com.example.demo.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Formulario;

public interface IformularioService {
	public List<Formulario>listar();
	public Optional<Formulario>listarId(int id);
	public int save(Formulario f);
	public void delete(int id);
}
