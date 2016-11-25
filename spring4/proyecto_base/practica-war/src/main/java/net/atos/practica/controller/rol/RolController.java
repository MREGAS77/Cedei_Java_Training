package net.atos.practica.controller.rol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import net.atos.practica.busines.permiso.PermisoBO;
import net.atos.practica.busines.rol.RolBO;
import net.atos.practica.dto.FiltroPermisosDto;
import net.atos.practica.dto.FiltroRolesDto;
import net.atos.practica.entity.Permiso;
import net.atos.practica.entity.Rol;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.context.Theme;


@Component
@Transactional
public class RolController {
	protected FiltroRolesDto filtro = new FiltroRolesDto();
	protected List<Rol> lista;
	protected Rol rolSeleccionado;

	private DualListModel<Permiso> permisos= new DualListModel<Permiso>();  
	
	@Autowired
	RolBO rolBO;
	
	@Autowired
	PermisoBO permisoBO;
	
	@PostConstruct
    public void init() {
    }
    
    public void rellenarPickList() {
    	List<Permiso> permisosSource = permisoBO.buscar(new FiltroPermisosDto());
    	List<Permiso> permisosTarget = new ArrayList<Permiso> ();
        if (rolSeleccionado != null && !"".equals(rolSeleccionado.getNombre())) {
            permisosTarget.addAll(rolSeleccionado.getPermisos());
	        permisosSource.removeAll(permisosTarget);
        } else {
        	rolSeleccionado = new Rol();
        	
        }
        permisos = new DualListModel<Permiso>(permisosSource, permisosTarget);
    }
    	
	public void nuevoRol() {
		rolSeleccionado = new Rol();
		rellenarPickList();
	}
	
	public void insertar() {
		FiltroRolesDto f = new FiltroRolesDto();
		f.setNombre(rolSeleccionado.getNombre());
		if (rolBO.buscar(f).isEmpty()) {
			updatePermisosRolSeleccionado();
			rolBO.insertar(rolSeleccionado);
			buscar();
		} else {
			FacesContext.getCurrentInstance().addMessage("nombre", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ya existe un rol con ese nombre", "nombre"));
		}
	}
	
	public void actualizar() {
		updatePermisosRolSeleccionado();
		rolBO.actualizar(rolSeleccionado);
		buscar();
	}
	
	public void updatePermisosRolSeleccionado() {
		rolSeleccionado.getPermisos().clear();
		rolSeleccionado.getPermisos().addAll(permisos.getTarget());
	}
	
	public void borrar() {
		rolBO.borrar(rolSeleccionado);
		buscar();
	}
	
	public void buscar() {
		lista = rolBO.buscar(filtro);
	}

	public FiltroRolesDto getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroRolesDto filtro) {
		this.filtro = filtro;
	}

	public List<Rol> getLista() {
		return lista;
	}

	public void setLista(List<Rol> lista) {
		this.lista = lista;
	}

	public Rol getRolSeleccionado() {
		return rolSeleccionado;
	}

	public void setRolSeleccionado(Rol rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}
	

	public DualListModel<Permiso> getPermisos() {
        return permisos;
    }
 
    public void setPermisos(DualListModel<Permiso> permisos) {
        this.permisos = permisos;
    }    
}
