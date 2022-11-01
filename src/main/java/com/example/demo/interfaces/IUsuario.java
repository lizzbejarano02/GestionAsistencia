package com.example.demo.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Usuario;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Integer>{
	
	Optional<Usuario> findByCorreo(String correo);
	
	 @Query(value ="SELECT * FROM usuario c WHERE c.correo = ?1", nativeQuery = true)
	 public Usuario findByEmail(String correo);
	
	public Usuario findByResetPasswordToken(String token);
}
