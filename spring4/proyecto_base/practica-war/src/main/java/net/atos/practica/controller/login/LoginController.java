package net.atos.practica.controller.login;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import net.atos.common.identity.Identity;
import net.atos.practica.business.login.LoginBO;
import net.atos.practica.business.usuario.UsuarioBO;
import net.atos.practica.dto.FiltroUsuariosDto;
import net.atos.practica.entity.Usuario;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope("view")
@Transactional
public class LoginController {

	private static final Logger LOG = Logger.getLogger(LoginController.class);

	@Autowired
	LoginBO loginBO;
	
	@Autowired
	UsuarioBO usuarioBO;

	@Autowired
	Identity identity;

	private String password;
	private String msg;
	private String usuario;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	// validate login
	public String doLogin() {
		
		if (loginBO.validate(usuario, password)) {
			identity.setLoggedIn(true);
			identity.setUsuario(usuario);	
			
			// Añadido para settear los permisos del usuario que hace loggin en el Identity
			// que va a durar toda la sesión
			FiltroUsuariosDto filtro = new FiltroUsuariosDto();
			filtro.setNombre(usuario);
			filtro.setPasswd(password);
			List<Usuario> usuList = usuarioBO.buscar(filtro);
			if (!usuList.isEmpty()) {
				usuList.get(0).getRol().getPermisos().iterator();
				identity.setPermisos(usuList.get(0).getRol().getPermisos());
			}
			
			LOG.info("==== Usuario logado: " + usuario);

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("identity", identity);
			
			return "home.xhtml";
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o contraseña incorrectos", "Por favor, introduzca el usuario o contraseña válidos"));
		LOG.info("**** Usuario log incorrecto: " + usuario);
		return "false";

	}

	// logout event, invalidate session
	public String logout() {
		loginBO.logout();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
		}
		FacesMessage msg = new FacesMessage("Logout realizado", identity.getUsuario());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		identity.setLoggedIn(false);
		identity.setUsuario(null);

		return "/login.xhtml";
	}
}