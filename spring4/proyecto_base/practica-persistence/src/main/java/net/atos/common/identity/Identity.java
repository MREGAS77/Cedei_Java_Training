package net.atos.common.identity;

import java.util.HashSet;
import java.util.Set;

import net.atos.practica.entity.Permiso;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session")
public class Identity {
	protected String usuario;
	protected boolean loggedIn = false;
	protected Set<Permiso> permisos = new HashSet<Permiso>();
	

	private static final Logger LOG = Logger.getLogger(Identity.class);

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
		
	public Set<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}

	public Boolean tieneAcceso(String nombrePermiso) {
		Permiso p = new Permiso();
		p.setNombre(nombrePermiso);
		System.out.println("ALGO");
		return permisos.contains(p);
	}
	
}
