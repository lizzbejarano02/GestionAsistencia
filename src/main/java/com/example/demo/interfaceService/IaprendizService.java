package com.example.demo.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Aprendiz;

public interface IaprendizService {
	public List<Aprendiz>listar();
	public Optional<Aprendiz>listarId(int id);
	public int save(Aprendiz a);
	public void delete(int id);
}
