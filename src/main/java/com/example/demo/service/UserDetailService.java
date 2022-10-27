package com.example.demo.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.interfaceService.IusuarioService;
import com.example.demo.model.Usuario;

@Service
public class UserDetailService implements UserDetailsService{
	
	@Autowired
	private IusuarioService usuarioService;
	
	@Lazy
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	HttpSession session;
	
	
	private Logger log = LoggerFactory.getLogger(UserDetailService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Esto es el username");
		Optional<Usuario>optionaluser=usuarioService.findByCorreo(username);
		
		
		if(optionaluser.isPresent()) {
			log.info("Esto es el id del usuario: {}",optionaluser.get().getId());
			session.setAttribute("idusuario", optionaluser.get().getId());
			
			Usuario usuario = optionaluser.get();
			
			return User.builder().username(usuario.getNombre()).password(usuario.getPass()).roles(usuario.getTipo()).build();
		}else {
			throw new UsernameNotFoundException("usuario no encontrado");
		}
		
	}
	
}
