package com.example.model;

public class Comics {
	private int codigo;
	private String nombre;
	private String autor;
	private String editorial;
	private String resumen;
	private String genero;
	
	
	public Comics(int codigo, String nombre, String editorial, String autor, String resumen, String genero) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.editorial=editorial;
		this.autor = autor;
		this.resumen = resumen;
		this.genero = genero;
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getResumen() {
		return resumen;
	}
	
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	

	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	
}
