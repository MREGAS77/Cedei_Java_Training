package net.atos.practica.dto;

import java.util.Set;

import net.atos.practica.entity.Rol;

public class FiltroPermisosDto {
	protected String nombre;
	protected String descripcion;
	protected Set<Rol> roles;
	
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
	public Set<Rol> getRoles() {
		return roles;
	}
	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	

}
