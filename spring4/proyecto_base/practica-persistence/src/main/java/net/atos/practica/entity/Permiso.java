package net.atos.practica.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="PERMISOS")
public class Permiso {
	
	@Id
	@Length(min=1,max=30)
	protected String nombre;
	@Length(min=0,max=255)
	protected String descripcion;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="permisos")
	private Set<Rol> roles;
		
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Permiso))
			return false;
		Permiso other = (Permiso) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	public String toString() {
		return getNombre();
	}
	
	
}
