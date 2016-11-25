package net.atos.practica.dto;

import java.util.Set;

import net.atos.practica.entity.Permiso;

public class FiltroRolesDto {
	protected String nombre;
	protected String descripcion;
	protected Set<Permiso> permisos;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Set<Permiso> getPermisos() {
		return permisos;
	}
	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}
	
	
	
	
}
