package net.atos.practica.business.login;

import net.atos.practica.dao.UsuarioDao;
import net.atos.practica.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Component
public class LoginBO {

	@Autowired
	UsuarioDao usuarioDao;
	
	public boolean validate(String usuario, String password) {
		
		Usuario u = usuarioDao.buscarUsuarioPorUsernameYPassword(usuario, password);
		return (u != null);
	}


	public void logout() {
		
	}
	
	
}
