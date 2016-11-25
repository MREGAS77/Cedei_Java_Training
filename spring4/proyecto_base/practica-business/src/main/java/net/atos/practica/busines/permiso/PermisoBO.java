package net.atos.practica.busines.permiso;

import java.util.List;

import javax.persistence.EntityManager;

import net.atos.practica.dao.PermisosDao;
import net.atos.practica.dto.FiltroPermisosDto;
import net.atos.practica.entity.Permiso;
import net.atos.practica.entity.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation=Propagation.REQUIRED)
public class PermisoBO {
	
	@Autowired
	PermisosDao permisosDao;
	
	public void insertar(Permiso p) {
		permisosDao.insertOrUpdate(p);
	}
	
	public Permiso actualizar(Permiso p) {
		return permisosDao.insertOrUpdate(p);
	}
	
	public void borrar(Permiso p) {
		permisosDao.deleteOne(p);
	}
	
	public List<Permiso> buscar(FiltroPermisosDto filtro) {
		return permisosDao.buscar(filtro);
	}

}
