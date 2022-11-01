package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.interfaceService.IusuarioService;
import com.example.demo.interfaces.IUsuario;
import com.example.demo.model.Usuario;
import com.example.demo.service.UploadFileService;



@Controller
@RequestMapping("/admin")
public class Admincontroller {
	
	@Autowired
	private IusuarioService service;
	
	@Autowired
	private UploadFileService upload;
	
	
	private final Logger log= LoggerFactory.getLogger(Admincontroller.class);
	
	BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
	
	@GetMapping("/listar")
	public String listarUsuario(Model model, HttpSession session, Usuario usuario) {
		
		
		log.info("sesion del usuario: {}", session.getAttribute("idusuario"));
		List<Usuario>usuarios=service.listar();
		model.addAttribute("usuarios", usuarios);
		return "admin/index";
	}
	
	@GetMapping("/new")
	public String agregarAdmin(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "admin/form";
		
	}
	
	@PostMapping("/save")
	public String saveUsuario(@Validated int id, Usuario u,@RequestParam("img") MultipartFile file) throws IOException {
		u.setPass(passEncode.encode(u.getPass()));
		//imagen
				if (u.getId()==0) { // cuando se crea un producto
					String nombreImagen= upload.saveImage(file);
					u.setImagen(nombreImagen);
				}else {
					
				}
				
		service.save(u);
		return "redirect:/admin/listar";
		
	}
	
	@GetMapping("/editar/{id}")
	public String editarAdmin (@PathVariable int id, Model model) {
		Optional<Usuario>usuario=service.listarId(id);
		model.addAttribute("usuario", usuario);
		return "admin/update";
		
	}
	
	@PostMapping("/update")
	public String update(Usuario usuario, @RequestParam("img") MultipartFile file ) throws IOException {
		Usuario u= new Usuario();
		u=service.listarId(usuario.getId()).get();
		
		if (file.isEmpty()) { // editamos el producto pero no cambiamos la imagem
			
			usuario.setImagen(u.getImagen());
		}else {// cuando se edita tbn la imagen			
			//eliminar cuando no sea la imagen por defecto
			if (!u.getImagen().equals("default.jpg")) {
				upload.deleteImage(u.getImagen());
			}
			String nombreImagen= upload.saveImage(file);
			usuario.setImagen(nombreImagen);
		}
		service.update(usuario);		
		return "redirect:/admin/listar";
	}
	
	
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable int id) {
		Usuario u = new Usuario();
		u=service.listarId(id).get();
		
		//eliminar cuando no sea la imagen por defecto
		if (!u.getImagen().equals("default.jpg")) {
			upload.deleteImage(u.getImagen());
		}
		
		service.delete(id);
		return "redirect:/admin/listar";
	}
	
	@GetMapping("/d")
	public String d() {
		return "dashboard/dashboard";
	}
	
}
