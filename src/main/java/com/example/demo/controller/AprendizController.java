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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.interfaces.IArea;
import com.example.demo.model.Aprendiz;
import com.example.demo.model.Area;
import com.example.demo.service.AprendizService;


@Controller
@RequestMapping("/aprendiz")
public class AprendizController {
	
	@Autowired
	private AprendizService aprendizService;
	
	@Autowired
	private IArea iArea;
	
	@GetMapping("/listar")
	public String listarAprendiz(Model model) {
		List<Aprendiz>aprendices=aprendizService.listar();
		model.addAttribute("aprendices", aprendices);
		return "aprendiz/vistaAprendiz";
	}
	
	@GetMapping("/new")
	public String nuevoAprendiz(Model model) {
		List<Area>lstArea= (List<Area>)iArea.findAll();
		model.addAttribute("lstArea", lstArea);
		model.addAttribute("aprendiz", new Aprendiz());
		return "aprendiz/formulario";
		
	}
	
	@PostMapping("/save")
	public String guardarAprendiz(@Validated int id, Aprendiz a, Model model) {
		List<Area>lstArea= (List<Area>)iArea.findAll();
		model.addAttribute("lstArea", lstArea);
		aprendizService.save(a);
		return "redirect:/aprendiz/listar";
	}
	
	@GetMapping("/edit/{id}")
	public String editarAprendiz(@PathVariable int id, Model model) {
		List<Area>lstArea= (List<Area>)iArea.findAll();
		model.addAttribute("lstArea", lstArea);
		Optional<Aprendiz>aprendiz=aprendizService.listarId(id);
		model.addAttribute("aprendiz", aprendiz);
		return "aprendiz/formulario";
		
	}
	
	@RequestMapping("/estadoAprendiz")
    public String estadoAprendiz(
    @RequestParam(name = "id", defaultValue = "0") Integer id) {
        Aprendiz aux = aprendizService.listarId(id).orElse(null);
        if(aux.getEstado().equals("activo")){
            aux.setEstado("inactivo");
            aprendizService.save(aux);
        } else if(aux.getEstado().equals("inactivo")){
            aux.setEstado("activo");
            aprendizService.save(aux);
        }
         return "redirect:/aprendiz/listar";
    }
}
