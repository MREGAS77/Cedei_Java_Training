package net.atos.practica.controller.usuario;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//import net.atos.bluekiwi.BluekiwiExtractor;
//import net.atos.bluekiwi.dto.FeedSummary;
//import net.atos.bluekiwi.dto.Mensaje;
//import net.atos.bluekiwi.dto.feed.Feed;
import net.atos.practica.busines.rol.RolBO;
import net.atos.practica.business.usuario.UsuarioBO;
import net.atos.practica.dto.FiltroRolesDto;
import net.atos.practica.dto.FiltroUsuariosDto;
import net.atos.practica.entity.Rol;
import net.atos.practica.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Scope("view")

public class UsuarioController {

	protected FiltroUsuariosDto filtro = new FiltroUsuariosDto();
	protected List<Usuario> lista;
	protected Usuario usuarioSeleccionado = new Usuario();
	protected List<Rol> comboRoles;

	@Autowired
	UsuarioBO usuarioBO;
	
	@Autowired
	RolBO rolBO;

	@PostConstruct
	public void init() {
		comboRoles = rolBO.buscar(new FiltroRolesDto());
		// Meto un Rol vacío en el filtro para que se muestre en el combo pero en el filtro pongo
		// un filtro vacío
		filtro.setRol(new Rol());
		usuarioSeleccionado.setRol(new Rol());
		
	}

	public void nuevoUsuario() {
		usuarioSeleccionado = new Usuario();
		usuarioSeleccionado.setRol(new Rol());
	}

	public void insertar() {
		FiltroUsuariosDto f = new FiltroUsuariosDto();
		f.setCodigo(usuarioSeleccionado.getCodigo());
		if (usuarioBO.buscar(f).isEmpty()) {
			usuarioBO.insertarOActualizar(usuarioSeleccionado);
			buscar();
		} else {
			//Mostrar mensaje de que ya está en la base de datos
			//FacesContext.getCurrentInstance().addMessage("codigo", new FacesMessage("Ya existe uno con ese nombre"));
			FacesContext.getCurrentInstance().addMessage("codigo", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ya existe un usuario con ese código", "codigo"));

		}
	}

	public void actualizar() {
		usuarioBO.insertarOActualizar(usuarioSeleccionado);
		buscar();
	}

	public void borrar() {
		usuarioBO.borrar(usuarioSeleccionado);
		buscar();
	}

	public void buscar() {
		lista = usuarioBO.buscar(filtro);
		
	}
	
	public void probar() {

	}

	public FiltroUsuariosDto getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroUsuariosDto filtro) {
		this.filtro = filtro;
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<Rol> getComboRoles() {
		return comboRoles;
	}

	public void setComboRoles(List<Rol> comboRoles) {
		this.comboRoles = comboRoles;
	}
	
	public void setRolVacio() {
		usuarioSeleccionado.setRol(new Rol());
	}
	
}
