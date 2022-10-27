package com.example.demo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Area;

@Repository
public interface IArea extends CrudRepository<Area, Integer>{

}
