package net.atos.practica.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="ROLES")
public class Rol {

	@Id
	@Length(min=1,max=30)
	private String nombre;
	@Length(min=0,max=255)
	private String descripcion;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="rol")
	Set<Usuario> usuarios = new HashSet<Usuario>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		      name="ROLES_PERMISOS",
		      joinColumns=@JoinColumn(name="NOMBRE_ROL", referencedColumnName="NOMBRE"),
		      inverseJoinColumns=@JoinColumn(name="NOMBRE_PERMISO", referencedColumnName="NOMBRE"))
	Set<Permiso> permisos = new HashSet<Permiso>();
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Set<Permiso> getPermisos() {
		return permisos;
	}
	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}
	public String getNombrePermisos() {
		return getPermisos().toString();
	}
}
