package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="aprendiz")
public class Aprendiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int documento;
	private String nombre;
	private String apellido;
	private String programa;
	private int ficha;
	private String estado;
	
	public Aprendiz() {
		
	}

	

	public Aprendiz(int id, int documento, String nombre, String apellido, String programa, int ficha, String estado,
			Area area) {
		super();
		this.id = id;
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.programa = programa;
		this.ficha = ficha;
		this.estado = estado;
		this.area = area;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getDocumento() {
		return documento;
	}



	public void setDocumento(int documento) {
		this.documento = documento;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getPrograma() {
		return programa;
	}



	public void setPrograma(String programa) {
		this.programa = programa;
	}



	public int getFicha() {
		return ficha;
	}



	public void setFicha(int ficha) {
		this.ficha = ficha;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public Area getArea() {
		return area;
	}



	public void setArea(Area area) {
		this.area = area;
	}

	

	@Override
	public String toString() {
		return "Aprendiz [id=" + id + ", documento=" + documento + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", programa=" + programa + ", ficha=" + ficha + ", estado=" + estado + ", area=" + area + "]";
	}



	@ManyToOne()
	@JoinColumn(name="id_area")
	private Area area;
}
