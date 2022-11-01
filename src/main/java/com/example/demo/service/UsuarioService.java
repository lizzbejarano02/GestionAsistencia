package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaceService.IusuarioService;
import com.example.demo.interfaces.IUsuario;
import com.example.demo.model.Usuario;



@Service
public class UsuarioService implements IusuarioService{
	
	@Autowired
	private IUsuario data;
	
	@Override
	public List<Usuario> listar() {
		return (List<Usuario>)data.findAll();
	}

	@Override
	public Optional<Usuario> listarId(int id) {
		return data.findById(id);
	}

	@Override
	public int save(Usuario u) {
		int res = 1;
		Usuario usuario = data.save(u);
		if(usuario.equals(null)) {
			res = 0;
		}
		
		return res;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);
	}

	@Override
	public void update(Usuario usuario) {
		data.save(usuario);
		
	}

	@Override
	public Optional<Usuario> findByCorreo(String correo) {
		return data.findByCorreo(correo);
	}

}
