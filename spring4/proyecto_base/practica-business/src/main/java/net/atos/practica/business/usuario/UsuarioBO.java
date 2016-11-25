package net.atos.practica.business.usuario;

import java.util.List;

import net.atos.common.utils.GenericUtils;
import net.atos.practica.dao.RolesDao;
import net.atos.practica.dao.UsuarioDao;
import net.atos.practica.dto.FiltroUsuariosDto;
import net.atos.practica.entity.Rol;
import net.atos.practica.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation=Propagation.REQUIRED)
public class UsuarioBO {
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@Autowired
	RolesDao rolDao;
		
	public void insertarOActualizar(Usuario u) {
		if (u.getRol() != null && !GenericUtils.isNullOrBlank(u.getRol().getNombre())) {
			u.setRol(rolDao.getReference(u.getRol().getNombre()));
		} else {
			u.setRol(new Rol());
		}
		usuarioDao.insertOrUpdate(u);
	}
	
	public void borrar(Usuario u) {
		usuarioDao.deleteOne(u);
	}
	
	public List<Usuario> buscar(FiltroUsuariosDto filtro) {
		return usuarioDao.buscar(filtro);
	}
	
}
