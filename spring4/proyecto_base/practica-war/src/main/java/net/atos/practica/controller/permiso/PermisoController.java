package net.atos.practica.controller.permiso;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import net.atos.practica.busines.permiso.PermisoBO;
import net.atos.practica.dto.FiltroPermisosDto;
import net.atos.practica.entity.Permiso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PermisoController {

	protected FiltroPermisosDto filtro = new FiltroPermisosDto();
	protected List<Permiso> lista;
	protected Permiso permisoSeleccionado;
	
	@Autowired
	PermisoBO permisoBO;
	
	@PostConstruct
	public void init() {
//		WebApplicationContext ctx =  FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
//		usuarioBO = ctx.getBean(UsuarioBO.class);
	}
	
	public void nuevoPermiso() {
		permisoSeleccionado = new Permiso();
	}
	
	
	public void insertar() {
		FiltroPermisosDto f = new FiltroPermisosDto();
		f.setNombre(permisoSeleccionado.getNombre());
		if (permisoBO.buscar(f).isEmpty()) {
			permisoBO.insertar(permisoSeleccionado);
			buscar();
		} else {
			FacesContext.getCurrentInstance().addMessage("nombre", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ya existe un Permiso con ese nombre", "nombre"));
		}
	}
	
	public Permiso actualizar() {
		Permiso updated =  permisoBO.actualizar(permisoSeleccionado);
		buscar();
		return updated;
	}
	
	public void borrar() {
		permisoBO.borrar(permisoSeleccionado);
		buscar();
	}
	
	public void buscar() {
		lista = permisoBO.buscar(filtro);
	}

	public FiltroPermisosDto getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroPermisosDto filtro) {
		this.filtro = filtro;
	}

	public List<Permiso> getLista() {
		return lista;
	}

	public void setLista(List<Permiso> lista) {
		this.lista = lista;
	}

	public Permiso getPermisoSeleccionado() {
		return permisoSeleccionado;
	}

	public void setPermisoSeleccionado(Permiso permisoSeleccionado) {
		this.permisoSeleccionado = permisoSeleccionado;
	}
	
}
