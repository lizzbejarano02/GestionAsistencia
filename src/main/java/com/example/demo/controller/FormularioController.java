package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.interfaceService.IaprendizService;
import com.example.demo.interfaceService.IformularioService;
import com.example.demo.model.Aprendiz;
import com.example.demo.model.Formulario;

@Controller
@RequestMapping("/formulario")
public class FormularioController {
	
	@Autowired
	private IformularioService iformularioService;
	
	@Autowired
	private IaprendizService iaprendizService;
	
	@GetMapping("/listar")
	public String listarFormulario(Model model) {
		List<Aprendiz>lstaprendiz=iaprendizService.listar();
		model.addAttribute("lstaprendiz", lstaprendiz);
		List<Formulario>formularios=iformularioService.listar();
		model.addAttribute("formularios", formularios);
		return "formulario/VistaFormulario";
	}
	
	@GetMapping("/new")
	public String nuevoFormulario(Model model, Formulario formulario) {
		List<Aprendiz>lstaprendiz=iaprendizService.listar();
		model.addAttribute("lstaprendiz", lstaprendiz);
		model.addAttribute("formulario", new Formulario());
		return "formulario/form";
		
	}
	
	@GetMapping("/success")
	public String nuevoFormularios(Model model, Formulario formulario) {
		List<Aprendiz>lstaprendiz=iaprendizService.listar();
		model.addAttribute("lstaprendiz", lstaprendiz);
		model.addAttribute("formulario", new Formulario());
		return "formulario/form_success";
		
	}
	
	@PostMapping("/save")
	public String saveFormulario(Formulario f) {
		iformularioService.save(f);
		return "redirect:/formulario/success";
	}
	
	@GetMapping("/edit/{id}")
	public String editFormulario(@PathVariable int id, Model model) {
		Optional<Formulario>formulario=iformularioService.listarId(id);
		model.addAttribute("formulario", formulario);
		return "editarFormulario";
		
	}
}
