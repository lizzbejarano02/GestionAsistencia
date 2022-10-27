package com.example.demo.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.interfaces.IUsuario;
import com.example.demo.model.Usuario;

@Service
@Transactional
public class UServiceForgot {
	
	@Autowired
    private IUsuario iUsuario;
	
	public void updateResetPasswordToken(String token, String correo) throws NoSuchElementException  {
        Usuario usuario = iUsuario.findByEmail(correo);
        if (usuario != null) {
        	usuario.setResetPasswordToken(token);
        	iUsuario.save(usuario);
        } else {
            throw new NoSuchElementException ("Could not find any customer with the email " + correo);
        }
    }
     
    public Usuario getByResetPasswordToken(String token) {
        return iUsuario.findByResetPasswordToken(token);
    }
     
    public void updatePassword(Usuario usuario, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        usuario.setPass(encodedPassword);
         
        usuario.setResetPasswordToken(null);
        iUsuario.save(usuario);
    }
}
