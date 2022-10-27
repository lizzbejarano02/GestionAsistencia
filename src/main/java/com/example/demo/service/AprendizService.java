package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaceService.IaprendizService;
import com.example.demo.interfaces.IAprendiz;
import com.example.demo.model.Aprendiz;

@Service
public class AprendizService implements IaprendizService{
	
	@Autowired
	private IAprendiz iaprendiz;
	@Override
	public List<Aprendiz> listar() {
		return (List<Aprendiz>)iaprendiz.findAll();
	}

	@Override
	public Optional<Aprendiz> listarId(int id) {
		return iaprendiz.findById(id);
	}

	@Override
	public int save(Aprendiz a) {
		int res = 1;
		Aprendiz aprendiz = iaprendiz.save(a);
		if(aprendiz.equals(null)) {
			res = 0;
		}
		return res;
	}

	@Override
	public void delete(int id) {
		iaprendiz.deleteById(id);
		
	}

}
