package com.example.demo.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="formulario")
public class Formulario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	private Date dia = new Date(System.currentTimeMillis());
	@Temporal(TemporalType.TIME)
	private Date hora = new Date(System.currentTimeMillis());
	
	public Formulario() {
		
	}
	

	public Formulario(Integer id, Date dia, Date hora, List<Aprendiz> aprendices) {
		super();
		this.id = id;
		this.dia = dia;
		this.hora = hora;
		this.aprendices = aprendices;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDia() {
		return dia;
	}


	public void setDia(Date dia) {
		this.dia = dia;
	}


	public Date getHora() {
		return hora;
	}


	public void setHora(Date hora) {
		this.hora = hora;
	}


	public List<Aprendiz> getAprendices() {
		return aprendices;
	}


	public void setAprendices(List<Aprendiz> aprendices) {
		this.aprendices = aprendices;
	}


	@ManyToMany
	@JoinTable(
		name="a_f",
		joinColumns = {@JoinColumn(name="id_formulario")},
		inverseJoinColumns = {@JoinColumn(name="id_aprendiz")}
		)
	
	private List<Aprendiz> aprendices;
}
