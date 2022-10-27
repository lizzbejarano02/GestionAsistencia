package com.example.demo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Aprendiz;

@Repository
public interface IAprendiz extends CrudRepository<Aprendiz, Integer>{

}
