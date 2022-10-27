package com.example.demo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Formulario;

@Repository
public interface IFormulario extends CrudRepository<Formulario, Integer>{

}
