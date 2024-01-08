package com.mifel.apirest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "poke")
public class Poke {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String tipo1;
	private String tipo2;
	private float peso;
	private float altura;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setName(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTipo1() {
		return tipo1;
	}
	
	public void setTipo1(String tipo1) {
		this.tipo1 = tipo1;
	}
	
	public String getTipo2() {
		return tipo2;
	}
	
	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}
	
	public float getPeso() {
		return peso;
	}
	
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	public float getAltura() {
		return altura;
	}
	
	public void setAltura(float altura) {
		this.altura = altura;
	}
}
